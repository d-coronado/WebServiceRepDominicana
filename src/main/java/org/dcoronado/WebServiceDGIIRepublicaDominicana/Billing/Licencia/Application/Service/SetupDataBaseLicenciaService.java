package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.SetupBDCommand;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.SetupDatabaseLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.DatabaseManagerPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DbConnectionInfo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.ScriptDataBaseExecutorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Servicio responsable de realizar el setup de base de datos para una licencia.
 * Este proceso incluye la validación de los datos de entrada, creación de la base de datos,
 * usuario, asignación de privilegios, ejecución de scripts de estructura y actualización del
 * estado del setup en la licencia.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SetupDataBaseLicenciaService implements SetupDatabaseLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final DatabaseManagerPort databaseManagerPort;
    private final ScriptDataBaseExecutorPort scriptExecutorPort;


    /**
     * Ejecuta el proceso completo de configuración de la base de datos para la licencia indicada.
     *
     * @param command Objeto que contiene los datos necesarios para configurar la base de datos.
     * @throws InvalidArgumentException si el setup ya fue completado previamente o los datos son inválidos.
     * @throws InfrastructureException  si ocurre un error durante la creación o configuración de la base de datos.
     * @throws NotFoundException        si no se encuentra la licencia asociada al RNC indicado.
     */
    @Transactional
    @Override
    public void execute(SetupBDCommand command) {

        log.info("INICIO - Proceso de setup para licencia con RNC {}", command.rnc());

        log.info("[1] Validando rnc de la licencia");
        final RNC rncValue = RNC.of(command.rnc());

        // Busca la licencia
        log.info("[2] Buscando licencia por RNC {}", rncValue);
        Licencia licenciaSaved = licenciaRepositoryPort.findByRnc(rncValue.getValor())
                .orElseThrow(() -> new NotFoundException("Licencia not found"));

        log.info("[3] Generando datos para la creación del setup de BD");
        licenciaSaved.prepararSetupBD(command.host(), command.puerto());

        try {

            // Crear base de datos
            log.info("[4] Creando base de datos '{}'", licenciaSaved.getConfiguracionBD().getNombreBD());
            databaseManagerPort.createDatabase(licenciaSaved.getConfiguracionBD().getNombreBD());

            // Crear usuario
            log.info("[5] Creando usuario '{}'", licenciaSaved.getConfiguracionBD().getUsuario());
            databaseManagerPort.createUser(licenciaSaved.getConfiguracionBD().getUsuario(), licenciaSaved.getConfiguracionBD().getPassword(), licenciaSaved.getConfiguracionBD().getHostBD());

            // Otorgar privilegios
            log.info("[6] Otorgando privilegios");
            databaseManagerPort.grantPrivileges(licenciaSaved.getConfiguracionBD().getNombreBD(), licenciaSaved.getConfiguracionBD().getUsuario(), licenciaSaved.getConfiguracionBD().getHostBD());

            // Ejecutar script de estructura
            log.info("[7] Ejecutando script de estructura de BD");
            DbConnectionInfo dbConnectionInfo = DbConnectionInfo.builder()
                    .urlConexionBd(licenciaSaved.getConfiguracionBD().getUrlConexion())
                    .usuarioBd(licenciaSaved.getConfiguracionBD().getUsuario())
                    .passwordBd(licenciaSaved.getConfiguracionBD().getPassword())
                    .build();
            scriptExecutorPort.executeScript(dbConnectionInfo);

            log.info("[8] Guardando cambios");
            licenciaRepositoryPort.save(licenciaSaved);

            log.info("[9] Setup finalizado correctamente para RNC {}", licenciaSaved.getRnc());

        } catch (Exception e) {
            log.error("[E] Error durante setup de BD para RNC {}: {}", licenciaSaved.getRnc(), e.getMessage(), e);
            databaseManagerPort.dropUserIfExists(licenciaSaved.getConfiguracionBD().getUsuario(), licenciaSaved.getConfiguracionBD().getHostBD());
            log.info("Rollback realizado: usuario eliminado '{}@{}'", licenciaSaved.getConfiguracionBD().getUsuario(), licenciaSaved.getConfiguracionBD().getHostBD());
            throw new InfrastructureException("Error durante el proceso de setup de base de datos ", e);
        }
    }
}

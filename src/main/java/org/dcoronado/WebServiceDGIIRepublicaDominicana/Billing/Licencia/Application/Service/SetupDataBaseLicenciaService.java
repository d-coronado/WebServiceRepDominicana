package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.SetupDatabaseLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.DatabaseManagerPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DbConnectionInfo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.ScriptDataBaseExecutorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;



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
     * @param licenciaRequest Objeto que contiene los datos necesarios para configurar la base de datos.No debe ser nulo y debe contener un RNC válido.
     * @throws InvalidArgumentException si el setup ya fue completado previamente o los datos son inválidos.
     * @throws InfrastructureException si ocurre un error durante la creación o configuración de la base de datos.
     * @throws RuntimeException si no se encuentra la licencia asociada al RNC indicado.
     */
    @Override
    public void execute(Licencia licenciaRequest) {

        log.info("INICIO - Proceso de setup para licencia con RNC {}", licenciaRequest.getRnc());

        log.info("[1] Validando datos de entrada");
        licenciaRequest.validarDatosEntradaSetupBd();

        log.info("[2] Buscando licencia por RNC {}", licenciaRequest.getRnc());
        // Busca la licencia
        Licencia licenciaSaved = licenciaRepositoryPort.findByRnc(licenciaRequest.getRnc())
                .orElseThrow(() -> new RuntimeException("Licencia not found"));

        log.info("[3] Verificando estado de setup de base de datos");
        // Validar que el setup NO esté completado
        if (licenciaSaved.tieneSetupBDCompletado()) {
            throw new InvalidArgumentException(
                    "El setup de base de datos ya fue completado para RNC %s en %s"
                            .formatted(licenciaSaved.getRnc(), licenciaSaved.getDatabaseSetupAt()));
        }

        log.info("[4] Generando datos para la creación del setup de BD");
        licenciaSaved.generarDatosBD(licenciaRequest);

        try {

            // Crear base de datos
            log.info("[5] Creando base de datos '{}'", licenciaSaved.getNombreBd());
            databaseManagerPort.createDatabase(licenciaSaved.getNombreBd());

            // Crear usuario
            log.info("[6] Creando usuario '{}'@'{}'", licenciaSaved.getUsuarioBd(), licenciaSaved.getHostBd());
            databaseManagerPort.createUser(licenciaSaved.getUsuarioBd(), licenciaSaved.getPasswordBd(), licenciaSaved.getHostBd());

            // Otorgar privilegios
            log.info("[7] Otorgando privilegios");
            databaseManagerPort.grantPrivileges(licenciaSaved.getNombreBd(), licenciaSaved.getUsuarioBd(), licenciaSaved.getHostBd());

            // Ejecutar script de estructura
            log.info("[8] Ejecutando script de estructura de BD");
            DbConnectionInfo dbConnectionInfo = DbConnectionInfo.builder()
                    .urlConexionBd(licenciaSaved.getUrlConexionBd())
                    .usuarioBd(licenciaSaved.getUsuarioBd())
                    .passwordBd(licenciaSaved.getPasswordBd())
                    .build();
            scriptExecutorPort.executeScript(dbConnectionInfo);

            log.info("[9] Marcando setup como completado y guardando cambios");
            licenciaSaved.marcarSetupBDCompletado();
            licenciaRepositoryPort.save(licenciaSaved);

            log.info("[10] Setup finalizado correctamente para RNC {}", licenciaSaved.getRnc());

        } catch (Exception e) {
            log.error("[E] Error durante setup de BD para RNC {}: {}", licenciaSaved.getRnc(), e.getMessage(), e);
            databaseManagerPort.dropUserIfExists(licenciaSaved.getUsuarioBd(), licenciaSaved.getHostBd());
            log.info("Rollback realizado: usuario eliminado '{}@{}'", licenciaSaved.getUsuarioBd(), licenciaSaved.getHostBd());
            throw new InfrastructureException("Error durante el proceso de setup de base de datos ", e);
        }
    }
}

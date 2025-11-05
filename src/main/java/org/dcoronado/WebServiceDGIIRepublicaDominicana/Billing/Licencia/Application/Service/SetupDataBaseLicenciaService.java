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

@Slf4j
@Service
@RequiredArgsConstructor
public class SetupDataBaseLicenciaService implements SetupDatabaseLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final DatabaseManagerPort databaseManagerPort;
    private final ScriptDataBaseExecutorPort scriptExecutorPort;


    @Override
    public void execute(Licencia licenciaRequest) {

        log.info("[1] Iniciando proceso de setup para licencia con RNC {}", licenciaRequest.getRnc());

        log.info("[2] Validando datos de entrada");
        licenciaRequest.validarDatosEntradaSetupBd();

        log.info("[3] Buscando licencia por RNC {}", licenciaRequest.getRnc());
        // Busca la licencia
        Licencia licenciaSaved = licenciaRepositoryPort.findByRnc(licenciaRequest.getRnc())
                .orElseThrow(() -> new RuntimeException("Licencia not found"));

        log.info("[4] Verificando estado de setup de base de datos");
        // Validar que el setup NO esté completado
        if (licenciaSaved.tieneSetupBDCompletado()) {
            throw new InvalidArgumentException(
                    "El setup de base de datos ya fue completado para RNC %s en %s"
                            .formatted(licenciaSaved.getRnc(), licenciaSaved.getDatabaseSetupAt()));
        }

        log.info("[5] Generando datos para la creación del setup de BD");
        licenciaSaved.generarDatosBD(licenciaRequest);

        try {

            // Crear base de datos
            log.info("[6] Creando base de datos '{}'", licenciaSaved.getNombreBd());
            databaseManagerPort.createDatabase(licenciaSaved.getNombreBd());

            // Crear usuario
            log.info("[7] Creando usuario '{}'@'{}'", licenciaSaved.getUsuarioBd(), licenciaSaved.getHostBd());
            databaseManagerPort.createUser(licenciaSaved.getUsuarioBd(), licenciaSaved.getPasswordBd(), licenciaSaved.getHostBd());

            // Otorgar privilegios
            log.info("[8] Otorgando privilegios");
            databaseManagerPort.grantPrivileges(licenciaSaved.getNombreBd(), licenciaSaved.getUsuarioBd(), licenciaSaved.getHostBd());

            // Ejecutar script de estructura
            log.info("[9] Ejecutando script de estructura de BD");
            DbConnectionInfo dbConnectionInfo = DbConnectionInfo.builder()
                    .urlConexionBd(licenciaSaved.getUrlConexionBd())
                    .usuarioBd(licenciaSaved.getUsuarioBd())
                    .passwordBd(licenciaSaved.getPasswordBd())
                    .build();
            scriptExecutorPort.executeScript(dbConnectionInfo);

            log.info("[10] Marcando setup como completado y guardando cambios");
            licenciaSaved.marcarSetupBDCompletado();
            licenciaRepositoryPort.save(licenciaSaved);

            log.info("[11] Setup finalizado correctamente para RNC {}", licenciaSaved.getRnc());

        } catch (Exception e) {
            log.error("[E] Error durante setup de BD para RNC {}: {}", licenciaSaved.getRnc(), e.getMessage(), e);
            databaseManagerPort.dropUserIfExists(licenciaSaved.getUsuarioBd(), licenciaSaved.getHostBd());
            log.info("Rollback realizado: usuario eliminado '{}@{}'", licenciaSaved.getUsuarioBd(), licenciaSaved.getHostBd());
            throw new InfrastructureException("Error durante el proceso de setup de base de datos ", e);
        }
    }
}

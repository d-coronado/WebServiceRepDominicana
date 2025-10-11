package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.SetupDatabaseLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.DatabaseHostPortProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.DatabaseManagerPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DatabaseHostInfo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DatabaseSetupData;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DbConnectionInfo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.ScriptDataBaseExecutorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Service
@RequiredArgsConstructor
public class SetupDataBaseLicenciaService implements SetupDatabaseLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final DatabaseHostPortProviderPort databaseHostPortProviderPort;
    private final DatabaseManagerPort databaseManagerPort;
    private final ScriptDataBaseExecutorPort scriptExecutorPort;


    @Override
    public void execute(String rnc) {

        notBlank(rnc, "rnc required");

        // Busca la licencia
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new RuntimeException("Licencia not found"));

        // Validar que el setup NO esté completado
        if (licencia.tieneSetupBDCompletado()) {
            throw new InvalidArgumentException(
                    "El setup de base de datos ya fue completado para RNC %s en %s"
                            .formatted(rnc, licencia.getDatabaseSetupAt()));
        }

        /* Configuración dinámica de host y puerto */
        DatabaseHostInfo hostInfo = databaseHostPortProviderPort.provide();
        licencia.configurarHostBD(hostInfo.host(), hostInfo.port());

        licencia.generarDatosBD();
        /* Preparar datos para la creación de la BD */
        DatabaseSetupData dbData = DatabaseSetupData.builder()
                .rnc(licencia.getRnc())
                .nombreBd(licencia.getNombreBd())
                .usuarioBd(licencia.getUsuarioBd())
                .passwordBd(licencia.getPasswordBd())
                .host(licencia.getHostBd())
                .puerto(licencia.getPuertoBd())
                .build();

        String urlConexion = databaseManagerPort.execute(dbData);
        licencia.setUrlConexionBd(urlConexion);
        licencia.validarUrlConexionBD();

        /* Ejecutar script que crea la BD de la licencia */
        DbConnectionInfo dbConnectionInfo = DbConnectionInfo.builder()
                .urlConexionBd(licencia.getUrlConexionBd())
                .usuarioBd(licencia.getUsuarioBd())
                .passwordBd(licencia.getPasswordBd())
                .build();

        scriptExecutorPort.executeScript(dbConnectionInfo);

        licencia.marcarSetupBDCompletado();

        licenciaRepositoryPort.save(licencia);

    }
}

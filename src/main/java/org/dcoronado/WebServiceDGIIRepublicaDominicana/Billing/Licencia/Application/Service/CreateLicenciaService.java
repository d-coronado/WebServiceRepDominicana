package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.CreateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DatabaseHostInfo;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DatabaseSetupData;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.ScriptExecutionData;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.AlreadyExistsException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioNode;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioTreeBuilder.buildLicenciaTree;


/**
 * Servicio de aplicación encargado de crear una nueva licencia.
 * Orquesta la lógica necesaria para inicializar una licencia:
 * validaciones, creación de base de datos, ejecución de scripts,
 * manejo de directorios y persistencia en repositorio.
 */
@Service
@RequiredArgsConstructor
public class CreateLicenciaService implements CreateLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final DatabaseManagerPort databaseManagerPort;
    private final ScriptDataBaseExecutorPort scriptExecutorPort;
    private final DirectoryPort fileSystemPort;
    private final DatabaseHostPortProviderPort databaseHostPortProviderPort;

    /**
     * Crea una nueva licencia y ejecuta todo el proceso de inicialización.
     *
     * @param licencia objeto de dominio a crear
     * @return la licencia creada y persistida
     * @throws InvalidArgumentException si la licencia es null
     * @throws AlreadyExistsException si ya existe una licencia con el mismo RNC
     */
    @Override
    public Licencia createLicencia(Licencia licencia) {

        if (isNull(licencia)) {
            throw new InvalidArgumentException("La licencia no puede ser null");
        }

        /* Configuración dinámica de host y puerto */
        DatabaseHostInfo hostInfo = databaseHostPortProviderPort.provide();
        licencia.setHostBd(hostInfo.host());
        licencia.setPuertoBd(hostInfo.port());
        licencia.setDatosBD();

        /* Validaciones de negocio */
        licencia.validarGeneric();
        licencia.validarDatosConexionBD();

        /* Verificar duplicados */
        licenciaRepositoryPort.findByRnc(licencia.getRnc())
                .ifPresent(l -> {
                    throw new AlreadyExistsException(
                            "Licencia con RNC %s ya existe".formatted(licencia.getRnc()));
                });

        /* Preparar datos para la creación de la BD */
        DatabaseSetupData dbData = new DatabaseSetupData(
                licencia.getRnc(),
                licencia.getNombreBd(),
                licencia.getUsuarioBd(),
                licencia.getPasswordBd(),
                licencia.getHostBd(),
                licencia.getPuertoBd()
        );

        /* Crear estructura de directorios */
        DirectorioNode directoryTreeLicencia = buildLicenciaTree(licencia.getRnc());
        fileSystemPort.createDirectory(directoryTreeLicencia);

        /* Crear base de datos y usuario */
        String urlConexion = databaseManagerPort.execute(dbData);
        licencia.setUrlConexionBd(urlConexion);

        /* Validar que la URL de conexión generada sea válida */
        licencia.validarUrlConexionBD();

        /* Ejecutar script de inicialización */
        ScriptExecutionData scriptData = new ScriptExecutionData(
                licencia.getUrlConexionBd(),
                licencia.getUsuarioBd(),
                licencia.getPasswordBd()
        );
        scriptExecutorPort.executeScript(scriptData);

        /* Persistir licencia */
        return licenciaRepositoryPort.save(licencia);
    }


}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.CreateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioTreeBuilder;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.AlreadyExistsException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioNode;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioTreeBuilder.buildLicenciaTree;

@Service
@RequiredArgsConstructor
public class CreateLicenciaService implements CreateLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final DatabaseManagerPort databaseManagerPort;
    private final ScriptDataBaseExecutorPort scriptExecutorPort;
    private final DirectoryPort fileSystemPort;
    private final DatabaseHostPortProviderPort databaseHostPortProviderPort;

    @Override
    public Licencia createLicencia(Licencia licencia) {
        if (isNull(licencia)) throw new InvalidArgumentException("La licencia no puede ser null");
        databaseHostPortProviderPort.apply(licencia);
        licencia.setDatosBD();
        licencia.validarGeneric();
        licencia.validarDatosConexionBD();
        Optional<Licencia> existingLicencia = licenciaRepositoryPort.findByRnc(licencia.getRnc());
        if(existingLicencia.isPresent()) throw new AlreadyExistsException("Licencia con RNC " + licencia.getRnc() + " ya existe");
        DirectorioNode directoryTreeLicencia = buildLicenciaTree(licencia.getRnc());
        fileSystemPort.createDirectory(directoryTreeLicencia);
        databaseManagerPort.setupDatabaseForLicense(licencia);
        scriptExecutorPort.executeScript(licencia);
        return licenciaRepositoryPort.save(licencia);
    }


}

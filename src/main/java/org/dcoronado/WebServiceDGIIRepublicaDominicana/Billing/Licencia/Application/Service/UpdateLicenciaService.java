package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UpdateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioTreeBuilder;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioNode;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UpdateLicenciaService implements UpdateLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final ScriptDataBaseExecutorPort scriptExecutorPort;
    private final DirectoryPort fileSystemPort;

    @Override
    public Licencia updateLicencia(Licencia licencia) {
        if (isNull(licencia)) throw new InvalidArgumentException("La licencia no puede ser null");
        if (licencia.getId() == null) throw new IllegalArgumentException("La licencia debe tener un ID para actualizarse");
        Optional<Licencia> existingLicencia = licenciaRepositoryPort.findById(licencia.getId());
        if(existingLicencia.isEmpty()) throw new NotFoundException("Licencia con ID " + licencia.getId() + " no encontrada");
        if(!existingLicencia.get().getRnc().equals(licencia.getRnc())) throw new IllegalArgumentException("No puedes cambiar el rnc, porque sobre el se define" +
                " el nombre de la bd, el nombre del usuario de bd al momento de su creacion y la creacion de carpetas");
        licencia.validarGeneric();
        existingLicencia.get().actualizarDatos(licencia);
        existingLicencia.get().validarDatosConexionBD();
        DirectorioNode arbolDirectory = DirectorioTreeBuilder.buildLicenciaTree(existingLicencia.get().getRnc());
        fileSystemPort.createDirectory(arbolDirectory);
        scriptExecutorPort.executeScript(existingLicencia.get());
        licenciaRepositoryPort.save(existingLicencia.get());
        return licencia;
    }

}

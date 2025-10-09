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

import static java.util.Objects.isNull;


/**
 * Servicio de aplicación encargado de actualizar la información
 * de una licencia previamente registrada en el sistema.
 * Solo permite modificar datos de la licencia que no afectan
 * el proceso de configuración inicial del Setup de BD ni
 * parámetros críticos definidos por el RNC.
 */
@Service
@RequiredArgsConstructor
public class UpdateLicenciaService implements UpdateLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final DirectoryPort fileSystemPort;


    /**
     * Actualiza los datos de una licencia existente.
     * Este proceso excluye cualquier cambio relacionado con:
     * - El RNC de la licencia
     * - El nombre de la base de datos
     * - El usuario de la base de datos
     * - La estructura inicial de carpetas asociada al RNC
     *
     * @param licencia licencia con los datos a actualizar
     * @return la licencia actualizada
     * @throws InvalidArgumentException si la licencia es nula
     * @throws IllegalArgumentException si la licencia no tiene ID o si se intenta cambiar el RNC
     * @throws NotFoundException        si no existe una licencia con el ID indicado
     */
    @Override
    public Licencia updateLicencia(Licencia licencia) {
        if (isNull(licencia)) throw new InvalidArgumentException("La licencia no puede ser null");
        if (licencia.getId() == null)
            throw new IllegalArgumentException("La licencia debe tener un ID para actualizarse");

        Licencia licenciaPersistida = licenciaRepositoryPort.findById(licencia.getId())
                .orElseThrow(() -> new NotFoundException("Licencia con ID " + licencia.getId() + " no encontrada"));

        if (!licenciaPersistida.getRnc().equals(licencia.getRnc())) {
            throw new IllegalArgumentException(
                    "No puedes cambiar el RNC, ya que sobre él se definen " +
                            "el nombre de la base de datos, el usuario de BD y la creación de carpetas."
            );
        }

        licencia.validarGeneric();
        licenciaPersistida.actualizarDatos(licencia);
        licenciaPersistida.validarDatosConexionBD();

        DirectorioNode arbolDirectory = DirectorioTreeBuilder.buildLicenciaTree(licenciaPersistida.getRnc());
        fileSystemPort.createDirectory(arbolDirectory);
        licenciaRepositoryPort.save(licenciaPersistida);
        return licencia;
    }

}

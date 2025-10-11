package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UpdateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;


/**
 * Use Case responsable ÚNICAMENTE de actualizar datos editables de una licencia.
 * Responsabilidades:
 * - Actualizar campos de negocio (razón social, dirección, contacto, etc.)
 * - Validar que la licencia existe
 * - Validar que no se intente cambiar el RNC si ya tiene setup completado
 */
@Service
@RequiredArgsConstructor
public class UpdateLicenciaService implements UpdateLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;

    /**
     * Actualiza los datos editables de una licencia existente.
     * Si la licencia NO tiene setup completado:
     * - Permite cambiar todos los campos incluido el RNC
     * Si la licencia YA tiene setup completado:
     * - Solo permite cambiar campos editables (no RNC)
     *
     * @param licencia licencia con los datos a actualizar
     * @return la licencia actualizada y persistida
     * @throws InvalidArgumentException si la licencia es null o no tiene ID
     * @throws NotFoundException        si no existe una licencia con el ID indicado
     * @throws InvalidArgumentException si intenta cambiar el RNC después del setup
     */
    @Override
    public Licencia updateLicencia(Licencia licencia) {
        if (isNull(licencia)) throw new InvalidArgumentException("La licencia no puede ser null");

        if (licencia.getId() == null)
            throw new IllegalArgumentException("La licencia debe tener un ID para actualizarse");

        Licencia licenciaPersistida = licenciaRepositoryPort.findById(licencia.getId())
                .orElseThrow(() -> new NotFoundException("Licencia con ID " + licencia.getId() + " no encontrada"));


        licencia.validarDatosBasicos();

        boolean intentaCambiarRnc = !licenciaPersistida.getRnc().equals(licencia.getRnc());

        if (intentaCambiarRnc) {
            // Solo permitir cambio de RNC si NO hay setup completado
            licenciaPersistida.validarPuedeActualizar();
            licenciaPersistida.setRnc(licencia.getRnc());
        }

        licenciaPersistida.actualizarDatos(
                licencia.getRazonSocial(),
                licencia.getDireccionFiscal(),
                licencia.getAlias(),
                licencia.getNombreContacto(),
                licencia.getTelefonoContacto(),
                licencia.getAmbiente()
        );

        licenciaRepositoryPort.save(licenciaPersistida);
        return licencia;
    }

}

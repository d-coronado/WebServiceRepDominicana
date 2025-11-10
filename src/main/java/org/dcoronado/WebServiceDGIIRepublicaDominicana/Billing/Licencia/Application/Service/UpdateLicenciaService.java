package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.UpdateLicenciaCommand;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UpdateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * Use Case responsable ÚNICAMENTE de actualizar datos editables de una licencia.
 * Responsabilidades:
 * - Actualizar campos de negocio (razón social, dirección, contacto, etc.)
 * - Validar que la licencia existe
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateLicenciaService implements UpdateLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;

    /**
     * Actualiza los datos editables de una licencia existente.
     *
     * @param command objeto que contiene los datos para actualizar la licencia
     * @return la licencia actualizada y persistida
     * @throws NotFoundException si no existe una licencia con el ID indicado
     */
    @Override
    public Licencia updateLicencia(UpdateLicenciaCommand command) {

        log.info("INICIO - Proceso de actualización de licencia");

        log.info("[1] Validando datos de entrada");
        RNC rncValue = RNC.of(command.rnc());

        log.info("[2] Buscando licencia existente en el repositorio");
        Licencia licenciaSaved = licenciaRepositoryPort.findById(command.licenciaId())
                .orElseThrow(() -> new NotFoundException("Licencia con ID " + command.licenciaId() + " no encontrada"));

        log.info("[3] Actualizando datos de la licencia");
        licenciaSaved.actualizarDatos(
                rncValue,
                command.razonSocial(),
                command.direccionFiscal(),
                command.alias(),
                command.nombreContacto(),
                command.telefono(),
                command.ambiente()
        );

        log.info("[4] Guardando licencia actualizada en el repositorio");
        licenciaRepositoryPort.save(licenciaSaved);

        log.info("[5] Proceso de actualización completado exitosamente para RNC {}", licenciaSaved.getRnc().getValor());
        return licenciaSaved;
    }

}

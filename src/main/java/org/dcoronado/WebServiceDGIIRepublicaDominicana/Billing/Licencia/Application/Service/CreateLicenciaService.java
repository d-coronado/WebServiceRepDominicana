package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.CreateLicenciaCommand;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.CreateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.AlreadyExistsException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CreateLicenciaService implements CreateLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;

    /**
     * Crea una nueva licencia
     *
     * @param command objeto con los datos requeridos para crear la licencia
     * @return la licencia creada y persistida
     * @throws InvalidArgumentException si la licencia es null
     * @throws AlreadyExistsException   si ya existe una licencia con el mismo RNC c
     */
    @Override
    public Licencia createLicencia(CreateLicenciaCommand command) {

        log.info("INICIO - Creando licencia con RNC: {}", command.rnc());

        log.info("[1] Validando rnc de la licencia");
        final RNC rncValue = RNC.of(command.rnc());

        log.info("[2] Verificando registro duplicado para licencia con RNC {}", rncValue.getValor());
        /* Verificar duplicados */
        licenciaRepositoryPort.findByRnc(rncValue.getValor())
                .ifPresent(l -> {
                    throw new AlreadyExistsException(
                            "Licencia con RNC %s ya existe".formatted(rncValue.getValor()));
                });

        log.info("[1] Construyendo nueva instancia de Licencia");
        Licencia licencia = Licencia.crear(
                rncValue,
                command.razonSocial(),
                command.direccionFiscal(),
                command.ambiente(),
                command.alias(),
                command.nombreContacto(),
                command.telefono()
        );


        log.info("[3] Guardando cambios en el repositorio de licencias");
        /* Persistir licencia */
        return licenciaRepositoryPort.save(licencia);
    }

}

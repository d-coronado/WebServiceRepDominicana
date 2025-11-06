package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.CreateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.AlreadyExistsException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateLicenciaService implements CreateLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;

    /**
     * Crea una nueva licencia
     *
     * @param licencia objeto de dominio a crear
     * @return la licencia creada y persistida
     * @throws InvalidArgumentException si la licencia es null
     * @throws AlreadyExistsException   si ya existe una licencia con el mismo RNC c
     */
    @Override
    public Licencia createLicencia(Licencia licencia) {

        log.info("INICIO - Proceso de creación de nueva licencia");

        log.info("[1] Validando datos de entrada");
        if (isNull(licencia)) throw new InvalidArgumentException("La licencia no puede ser null");
        licencia.validarDatosBasicos();

        log.info("[2] Verificando registro duplicado para licencia con RNC {}", licencia.getRnc());
        /* Verificar duplicados */
        licenciaRepositoryPort.findByRnc(licencia.getRnc())
                .ifPresent(l -> {
                    throw new AlreadyExistsException(
                            "Licencia con RNC %s ya existe".formatted(licencia.getRnc()));
                });

        log.info("[4] Guardando cambios en el repositorio de licencias");
        /* Persistir licencia */
        return licenciaRepositoryPort.save(licencia);
    }

}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.CreateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.AlreadyExistsException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

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
     * @throws AlreadyExistsException   si ya existe una licencia con el mismo RNC
     */
    @Override
    public Licencia createLicencia(Licencia licencia) {

        if (isNull(licencia)) throw new InvalidArgumentException("La licencia no puede ser null");

        licencia.validarDatosBasicos();

        /* Verificar duplicados */
        licenciaRepositoryPort.findByRnc(licencia.getRnc())
                .ifPresent(l -> {
                    throw new AlreadyExistsException(
                            "Licencia con RNC %s ya existe".formatted(licencia.getRnc()));
                });

        /* Persistir licencia */
        return licenciaRepositoryPort.save(licencia);
    }

}

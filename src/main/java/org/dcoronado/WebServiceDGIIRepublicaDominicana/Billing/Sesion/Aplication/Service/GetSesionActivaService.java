package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.GetSesionActivaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.SesionRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetSesionActivaService implements GetSesionActivaUseCase {

    private final SesionRepositoryPort sesionRepositoryPort;

    @Override
    public Optional<Sesion> getSesionActiva(Sesion sesion) {
        sesion.validarParametrosGenericos();
        LocalDateTime ahoraUtc = LocalDateTime.now(ZoneOffset.UTC);
        return sesionRepositoryPort.findSesionActiveByRnc(sesion,ahoraUtc);
    }

}

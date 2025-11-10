package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.GetSesionActivaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.SesionInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.SesionProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SesionProviderAdapter implements SesionProvider {

    private final CrearSesionUseCase crearSesionUseCase;
    private final GetSesionActivaUseCase getSesionActivaUseCase;

    @Override
    public SesionInfoDto crear(String rnc, AmbienteEnum ambiente) throws Exception {
        Sesion sesion = new Sesion();
        sesion.setRnc(rnc);
        sesion.setAmbiente(ambiente);
        Sesion sesionCreada = crearSesionUseCase.crearSesion(sesion);
        return new SesionInfoDto(
                sesionCreada.getRnc(),
                sesionCreada.getToken()
        );
    }

    @Override
    public Optional<SesionInfoDto> obtenerSesionActiva(String rnc, AmbienteEnum ambiente) {
        Sesion sesion = new Sesion();
        sesion.setRnc(rnc);
        sesion.setAmbiente(ambiente);
        Optional<Sesion> sesionActiva = getSesionActivaUseCase.getSesionActiva(sesion);
        return sesionActiva.map(s -> new SesionInfoDto(s.getRnc(), s.getToken()));
    }
}

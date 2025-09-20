package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Factory;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Request.SesionRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class SesionFactory {

    public Sesion ofDto(SesionRequestDto request) {
        if(isNull(request)) throw new InvalidArgumentException("DTO no puede ser null");
        Sesion sesion = new Sesion();
        this.updateOfDto(request,sesion);
        return sesion;
    }

    public void updateOfDto(SesionRequestDto request, Sesion sesion) {
        sesion.setRnc(request.rnc());
        sesion.setAmbiente(request.ambiente());
    }

}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Transformer;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Response.SesionResponseDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Dto.Transformer.DtoTransformer;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class SesionTransformer extends DtoTransformer<SesionResponseDto, Sesion> {

    @Override
    public SesionResponseDto fromObject(Sesion sesion) {
        if(isNull(sesion)) throw new InvalidArgumentException("Licencia no puede ser null");
        return new SesionResponseDto(
                sesion.getId(),
                sesion.getRnc(),
                sesion.getAmbiente(),
                sesion.getExpedido(),
                sesion.getExpira(),
                sesion.getToken()
        );
    }
}
package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Response;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

import java.time.LocalDateTime;

public record SesionResponseDto(
        String rnc,
        Ambiente ambiente,
        LocalDateTime expedido,
        LocalDateTime expira,
        String token
) {
}

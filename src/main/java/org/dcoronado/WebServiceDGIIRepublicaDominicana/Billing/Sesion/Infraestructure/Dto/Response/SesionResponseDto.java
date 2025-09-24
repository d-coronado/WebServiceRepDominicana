package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Response;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record SesionResponseDto(
        Long id,
        String rnc,
        Ambiente ambiente,
        OffsetDateTime expedido,
        OffsetDateTime expira,
        String token
) {
}

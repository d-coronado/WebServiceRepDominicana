package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Response;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

import java.time.OffsetDateTime;

public record SesionResponseDto(
        Long id,
        String rnc,
        AmbienteEnum ambiente,
        OffsetDateTime expedido,
        OffsetDateTime expira,
        String token
) {
}

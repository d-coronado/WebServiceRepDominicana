package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto;

import lombok.Builder;

@Builder
public record DatabaseSetupData(
        String rnc,
        String nombreBd,
        String usuarioBd,
        String passwordBd,
        String host,
        String puerto
) {
}

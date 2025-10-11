package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto;

import lombok.Builder;

@Builder
public record DbConnectionInfo(
        String urlConexionBd,
        String usuarioBd,
        String passwordBd
) {
}

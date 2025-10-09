package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto;

public record DbConnectionInfo(
        String urlConexionBd,
        String usuarioBd,
        String passwordBd
) {
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto;

public record ScriptExecutionData(
        String urlConexionBd,
        String usuarioBd,
        String passwordBd
) {}

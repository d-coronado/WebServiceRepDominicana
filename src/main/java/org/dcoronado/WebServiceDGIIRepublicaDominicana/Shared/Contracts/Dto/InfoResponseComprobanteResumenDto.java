package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto;

import java.util.List;

public record InfoResponseComprobanteResumenDto(
        int codigo,
        String estado,
        List<Mensaje> mensajes,
        String encf,
        boolean secuenciaUtilizada
) {
    public record Mensaje(
            String codigo,
            String valor
    ) {
    }
}
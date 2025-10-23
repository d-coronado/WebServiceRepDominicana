package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response;

import java.util.List;

public record ResponseComprobanteResumenDgii(
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

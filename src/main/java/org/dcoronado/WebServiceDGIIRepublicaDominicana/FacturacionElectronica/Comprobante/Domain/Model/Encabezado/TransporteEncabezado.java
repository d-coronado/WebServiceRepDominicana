package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;

@Builder
public record TransporteEncabezado(
        String conductor,
        String documentoTransporte,
        String ficha,
        String placa,
        String rutaTransporte,
        String zonaTransporte,
        String numeroAlbaran
) {
}

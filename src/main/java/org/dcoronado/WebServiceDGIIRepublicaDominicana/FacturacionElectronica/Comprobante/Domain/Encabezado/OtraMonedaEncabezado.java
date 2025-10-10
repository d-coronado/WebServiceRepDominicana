package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Encabezado;

public record OtraMonedaEncabezado(
        String conductor,
        String documentoTransporte,
        String ficha,
        String placa,
        String rutaTransporte,
        String zonaTransporte,
        String numeroAlbaran
) {
}

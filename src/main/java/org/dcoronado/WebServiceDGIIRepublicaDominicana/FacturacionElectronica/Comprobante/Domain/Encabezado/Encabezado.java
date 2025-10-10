package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Encabezado;

public record Encabezado(
        String version,
        DocEncabezado docEncabezado,
        EmisorEncabezado emisorEncabezado,
        CompradorEncabezado compradorEncabezado,
        InformacionAdicionalEncabezado informacionAdicionalEncabezado,
        TransporteEncabezado transporteEncabezado,
        TotalesEncabezado totalesEncabezado,
        OtraMonedaEncabezado otraMonedaEncabezado
) {
}

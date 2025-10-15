package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;

@Builder
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

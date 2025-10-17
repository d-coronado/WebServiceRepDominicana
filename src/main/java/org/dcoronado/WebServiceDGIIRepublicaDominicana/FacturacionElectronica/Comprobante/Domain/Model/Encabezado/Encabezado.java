package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class Encabezado {
    String version;
    DocEncabezado docEncabezado;
    EmisorEncabezado emisorEncabezado;
    CompradorEncabezado compradorEncabezado;
    InformacionAdicionalEncabezado informacionAdicionalEncabezado;
    TransporteEncabezado transporteEncabezado;
    TotalesEncabezado totalesEncabezado;
    OtraMonedaEncabezado otraMonedaEncabezado;
}

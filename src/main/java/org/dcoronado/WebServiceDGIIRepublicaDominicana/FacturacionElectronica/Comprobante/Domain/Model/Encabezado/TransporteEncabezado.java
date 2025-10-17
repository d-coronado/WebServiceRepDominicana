package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class TransporteEncabezado {
    private final String conductor;
    private final String documentoTransporte;
    private final String ficha;
    private final String placa;
    private final String rutaTransporte;
    private final String zonaTransporte;
    private final String numeroAlbaran;
}

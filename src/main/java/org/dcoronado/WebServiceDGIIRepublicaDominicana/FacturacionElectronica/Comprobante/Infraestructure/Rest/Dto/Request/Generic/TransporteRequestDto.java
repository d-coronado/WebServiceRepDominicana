package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

public record TransporteRequestDto(
        String conductor,
        String documentoTransporte,
        String ficha,
        String placa,
        String rutaTransporte,
        String zonaTransporte,
        String numeroAlbaran
) {}

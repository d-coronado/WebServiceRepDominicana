package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic.*;

public record EncabezadoGenericoRequestDto(

        @JsonProperty("doc")
        DocRequestDto doc,

        @JsonProperty("emisor")
        EmisorRequestDto emisor,

        @JsonProperty("comprador")
        CompradorRequestDto comprador,

        @JsonProperty("transporte")
        TransporteRequestDto transporte,

        @JsonProperty("totales")
        TotalesRequestDto totales,

        @JsonProperty("otraMoneda")
        OtraMonedaRequestDto otraMoneda
) {}

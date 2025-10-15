package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request;

import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record SubTotalGenericoRequestDto(
        Integer nroLinea,

        @Size(max = 40)
        String tituloSubTotal,

        Integer orden,

        BigDecimal montoGravadoITBISTotal,
        BigDecimal montoGravadoITBISTasa1,
        BigDecimal montoGravadoITBISTasa2,
        BigDecimal montoGravadoITBISTasa3,

        BigDecimal subTotalITBIS,
        BigDecimal subTotalITBISTasa1,
        BigDecimal subTotalITBISTasa2,
        BigDecimal subTotalITBISTasa3,

        BigDecimal subTotalImpuestosAdicionales,
        BigDecimal subTotalExento,
        BigDecimal montoSubTotal
) {}

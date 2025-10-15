package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import java.math.BigDecimal;

public record SubTotal(
        Integer numeroSubTotal,
        String descripcionSubtotal,
        Integer orden,
        BigDecimal subTotalMontoGravadoTotal,
        BigDecimal subTotalMontoGravadoI1,
        BigDecimal subTotalMontoGravadoI2,
        BigDecimal subTotalMontoGravadoI3,
        BigDecimal subTotaITBIS,
        BigDecimal subTotaITBIS1,
        BigDecimal subTotaITBIS2,
        BigDecimal subTotaITBIS3,
        BigDecimal subTotalImpuestoAdicional,
        BigDecimal subTotalExento,
        BigDecimal montoSubTotal,
        Integer lineas
) {

}

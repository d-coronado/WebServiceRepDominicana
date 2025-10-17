package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public final class SubTotal {
    private final Integer numeroSubTotal;
    private final String descripcionSubtotal;
    private final Integer orden;
    private BigDecimal subTotalMontoGravadoTotal;
    private BigDecimal subTotalMontoGravadoI1;
    private BigDecimal subTotalMontoGravadoI2;
    private BigDecimal subTotalMontoGravadoI3;
    private BigDecimal subTotaITBIS;
    private BigDecimal subTotaITBIS1;
    private BigDecimal subTotaITBIS2;
    private BigDecimal subTotaITBIS3;
    private BigDecimal subTotalImpuestoAdicional;
    private BigDecimal subTotalExento;
    private BigDecimal montoSubTotal;
    private final Integer lineas;
}

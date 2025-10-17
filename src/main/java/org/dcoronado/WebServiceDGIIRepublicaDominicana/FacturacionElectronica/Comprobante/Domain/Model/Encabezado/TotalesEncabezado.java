package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public final class TotalesEncabezado {

    private BigDecimal montoGravadoTotal;
    private BigDecimal montoGravadoI1;
    private BigDecimal montoGravadoI2;
    private BigDecimal montoGravadoI3;
    private BigDecimal montoExento;
    private BigDecimal totalITBIS;
    private BigDecimal totalITBIS1;
    private BigDecimal totalITBIS2;
    private BigDecimal totalITBIS3;
    private BigDecimal montoImpuestoAdicional;
    private BigDecimal montoTotal;
    private BigDecimal montoNoFacturable;
    private BigDecimal montoPeriodo;
    private BigDecimal saldoAnterior;
    private BigDecimal montoAvancePago;
    private BigDecimal valorPagar;
    private BigDecimal totalITBISRetenido;
    private BigDecimal totalISRRetencion;
    private BigDecimal totalITBISPercepcion;
    private BigDecimal totalISRPercepcion;

    // Inmutables
    private final String itbs1;
    private final String itbs2;
    private final String itbs3;
    private final List<ImpuestoAdicional> impuestosAdicionales;

    @Getter
    @Setter
    @Builder
    public static final class ImpuestoAdicional {
        private final String tipoImpuesto;
        private BigDecimal tasaImpuestoAdicional;
        private BigDecimal montoImpuestoSelectivoConsumoEspecifico;
        private BigDecimal montoImpuestoSelectivoConsumoAdvalorem;
        private BigDecimal otrosImpuestosAdicionales;
    }
}

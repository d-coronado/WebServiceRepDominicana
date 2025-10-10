package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Encabezado;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TransporteEncabezado {

    // Inmutables
    private final String tipoMoneda;
    private final List<ImpuestoAdicionalOtraMoneda> listImpuestoAdicionalOtraMoneda;

    // Mutables
    private BigDecimal tipoCambio;
    private BigDecimal montoGravadoTotalOtraMoneda;
    private BigDecimal montoGravado1OtraMoneda;
    private BigDecimal montoGravado2OtraMoneda;
    private BigDecimal montoGravado3OtraMoneda;
    private BigDecimal montoExentoOtraMoneda;
    private BigDecimal totalITBISOtraMoneda;
    private BigDecimal totalITBIS1OtraMoneda;
    private BigDecimal totalITBIS2OtraMoneda;
    private BigDecimal totalITBIS3OtraMoneda;
    private BigDecimal montoImpuestoAdicionalOtraMoneda;
    private BigDecimal montoTotalOtraMoneda;

    @Getter
    @AllArgsConstructor
    public static class ImpuestoAdicionalOtraMoneda {
        private final String tipoImpuestoOtraMoneda;
        private BigDecimal tasaImpuestoAdicionalOtraMoneda;
        private BigDecimal montoImpuestoSelectivoConsumoEspecificoOtraMoneda;
        private BigDecimal montoImpuestoSelectivoConsumoAdvaloremOtraMoneda;
        private BigDecimal otrosImpuestosAdicionalesOtraMoneda;
    }
}

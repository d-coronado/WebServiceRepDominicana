package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public final class PaginaSubTotal {
    private final Integer paginaNo;
    private final Integer noLineaDesde;
    private final Integer noLineaHasta;
    private BigDecimal subtotalMontoGravadoPagina;
    private BigDecimal subtotalMontoGravado1Pagina;
    private BigDecimal subtotalMontoGravado2Pagina;
    private BigDecimal subtotalMontoGravado3Pagina;
    private BigDecimal subtotalExentoPagina;
    private BigDecimal subtotalItbisPagina;
    private BigDecimal subtotalItbis1Pagina;
    private BigDecimal subtotalItbis2Pagina;
    private BigDecimal subtotalItbis3Pagina;
    private BigDecimal subtotalImpuestoAdicionalPagina;
    private final SubtotalImpuestoAdicional subtotalImpuestoAdicional;
    private BigDecimal montoSubtotalPagina;
    private BigDecimal subtotalMontoNoFacturablePagina;

    @Getter
    @Setter
    @Builder
    public static final class SubtotalImpuestoAdicional {
        private BigDecimal subtotalImpuestoSelectivoConsumoEspecificoPagina;
        private BigDecimal subtotalOtrosImpuesto;
    }
}

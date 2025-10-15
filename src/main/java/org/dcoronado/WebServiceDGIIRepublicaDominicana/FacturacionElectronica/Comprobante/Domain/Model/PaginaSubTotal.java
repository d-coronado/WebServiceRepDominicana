package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaginaSubTotal(
        Integer paginaNo,
        Integer noLineaDesde,
        Integer noLineaHasta,
        BigDecimal subtotalMontoGravadoPagina,
        BigDecimal subtotalMontoGravado1Pagina,
        BigDecimal subtotalMontoGravado2Pagina,
        BigDecimal subtotalMontoGravado3Pagina,
        BigDecimal subtotalExentoPagina,
        BigDecimal subtotalItbisPagina,
        BigDecimal subtotalItbis1Pagina,
        BigDecimal subtotalItbis2Pagina,
        BigDecimal subtotalItbis3Pagina,
        BigDecimal subtotalImpuestoAdicionalPagina,
        SubtotalImpuestoAdicional subtotalImpuestoAdicional,
        BigDecimal montoSubtotalPagina,
        BigDecimal subtotalMontoNoFacturablePagina
) {

    @Builder
    public record SubtotalImpuestoAdicional(
            BigDecimal subtotalImpuestoSelectivoConsumoEspecificoPagina,
            BigDecimal subtotalOtrosImpuesto
    ) {
    }
}

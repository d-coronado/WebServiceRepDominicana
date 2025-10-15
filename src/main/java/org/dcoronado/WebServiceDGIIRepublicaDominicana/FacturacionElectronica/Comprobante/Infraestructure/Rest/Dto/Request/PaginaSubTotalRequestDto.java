package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic.SubTotalImpuestoAdicionalRequestDto;

import java.math.BigDecimal;

public record PaginaSubTotalRequestDto(
        Integer nroPagina, // Representa "PaginaNo"
        Integer nroLineaDesde, // Representa "NoLineaDesde"
        Integer nroLineaHasta, // Representa "NoLineaHasta"
        BigDecimal subtotalMontoGravadoPagina, // Representa "SubtotalMontoGravadoPagina"
        BigDecimal subtotalMontoGravado1Pagina, // Representa "SubtotalMontoGravado1Pagina"
        BigDecimal subtotalMontoGravado2Pagina, // Representa "SubtotalMontoGravado2Pagina"
        BigDecimal subtotalMontoGravado3Pagina, // Representa "SubtotalMontoGravado3Pagina"
        BigDecimal subtotalExentoPagina, // Representa "SubtotalExentoPagina"
        BigDecimal subtotalItbisPagina, // Representa "SubtotalItbisPagina"
        BigDecimal subtotalItbis1Pagina, // Representa "SubtotalItbis1Pagina"
        BigDecimal subtotalItbis2Pagina, // Representa "SubtotalItbis2Pagina"
        BigDecimal subtotalItbis3Pagina, // Representa "SubtotalItbis3Pagina"
        BigDecimal subtotalImpuestoAdicionalPagina, // Representa "SubtotalImpuestoAdicionalPagina"
        SubTotalImpuestoAdicionalRequestDto subtotalImpuestoAdicional, // Representa "SubtotalImpuestoAdicional"
        BigDecimal montoSubtotalPagina, // Representa "MontoSubtotalPagina"
        BigDecimal subtotalMontoNoFacturablePagina // Representa "SubtotalMontoNoFacturablePagina"
) {}

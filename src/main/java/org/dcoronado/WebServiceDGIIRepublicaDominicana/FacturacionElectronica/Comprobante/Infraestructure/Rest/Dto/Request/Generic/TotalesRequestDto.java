package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record TotalesRequestDto(
        BigDecimal montoGravadoTotal,
        BigDecimal montoGravadoITBIS1,
        BigDecimal montoGravadoITBIS2,
        BigDecimal montoGravadoITBIS3,
        BigDecimal montoExento,
        String tasaITBIS1,
        String tasaITBIS2,
        String tasaITBIS3,
        BigDecimal totalITBIS,
        BigDecimal totalITBISTasa1,
        BigDecimal totalITBISTasa2,
        BigDecimal totalITBISTasa3,
        BigDecimal montoImpuestoAdicional,
        @Size(max = 20, message = "{}")
        List<ImpuestoAdicionalRequestDto> impuestosAdicionales,
        BigDecimal montoTotal,
        BigDecimal montoNoFacturable,
        BigDecimal montoPeriodo,
        BigDecimal saldoAnteriorCobro,
        BigDecimal montoAvancePago,
        BigDecimal valorAPagar,
        BigDecimal montoTotalITBISRetenido,
        BigDecimal montoTotalRetencionRenta,
        BigDecimal montoTotalITBISPercepcion,
        BigDecimal montoTotalPercepcionRenta
) {}

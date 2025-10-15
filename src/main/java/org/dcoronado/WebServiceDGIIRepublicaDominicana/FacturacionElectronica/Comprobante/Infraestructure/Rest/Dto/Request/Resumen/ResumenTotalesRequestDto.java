package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Resumen;

import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public record ResumenTotalesRequestDto(
        BigDecimal montoGravadoTotal,
        BigDecimal montoGravadoITBIS1,
        BigDecimal montoGravadoITBIS2,
        BigDecimal montoGravadoITBIS3,
        BigDecimal montoExento,
        BigDecimal totalITBIS,
        BigDecimal totalITBISTasa1,
        BigDecimal totalITBISTasa2,
        BigDecimal totalITBISTasa3,
        BigDecimal montoImpuestoAdicional,
        @Size(max = 20, message = "{}")
        List<ResumenImpuestoAdicionalRequestDto> impuestosAdicionales,
        BigDecimal montoTotal,
        BigDecimal montoNoFacturable,
        BigDecimal montoPeriodo
) {}

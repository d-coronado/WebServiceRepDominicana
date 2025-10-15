package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public record OtraMonedaRequestDto(
        String codigoMoneda,
        BigDecimal tipoCambio,
        BigDecimal montoGravadoTotal,
        BigDecimal montoGravadoITBISTasa1,
        BigDecimal montoGravadoITBISTasa2,
        BigDecimal montoGravadoITBISTasa3,
        BigDecimal montoExento,
        BigDecimal totalITBIS,
        BigDecimal totalITBISTasa1,
        BigDecimal totalITBISTasa2,
        BigDecimal totalITBISTasa3,
        BigDecimal montoImpuestoAdicional,
        @Size(max = 20, message = "{}")
        List<ImpuestoAdicionalRequestDto> impuestosAdicionales,
        BigDecimal montoTotal
) {}

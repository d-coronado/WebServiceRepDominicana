package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Resumen;

import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ResumenImpuestoAdicionalRequestDto(
        @Size(max = 3, message = "{}")
        String codigoImpuesto,
        BigDecimal montoImpuestoSelectivoConsumoEspecifico,
        BigDecimal montoImpuestoSelectivoConsumoAdvalorem,
        BigDecimal otrosImpuestosAdicionales
) {}

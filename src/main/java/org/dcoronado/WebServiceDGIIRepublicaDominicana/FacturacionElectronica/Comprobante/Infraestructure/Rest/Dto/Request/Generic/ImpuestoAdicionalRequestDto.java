package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ImpuestoAdicionalRequestDto(

        @Size(max = 3, message = "{}")
        String codigoImpuesto,

        BigDecimal tasaImpuesto,
        BigDecimal montoImpuestoSelectivoConsumoEspecifico,
        BigDecimal montoImpuestoSelectivoConsumoAdvalorem,
        BigDecimal otrosImpuestosAdicionales
) {}

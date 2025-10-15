package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import java.math.BigDecimal;

public record SubTotalImpuestoAdicionalRequestDto(
        BigDecimal subtotalImpuestoSelectivoConsumoEspecificoPagina,
        BigDecimal subtotalOtrosImpuesto
) {}

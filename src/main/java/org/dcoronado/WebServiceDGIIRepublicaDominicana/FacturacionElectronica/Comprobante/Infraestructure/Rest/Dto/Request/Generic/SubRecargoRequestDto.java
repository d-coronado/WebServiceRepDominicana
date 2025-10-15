package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import java.math.BigDecimal;

public record SubRecargoRequestDto(
        String tipo, // $, %
        BigDecimal subRecargoPorcentaje,
        BigDecimal montoSubRecargo
) {}

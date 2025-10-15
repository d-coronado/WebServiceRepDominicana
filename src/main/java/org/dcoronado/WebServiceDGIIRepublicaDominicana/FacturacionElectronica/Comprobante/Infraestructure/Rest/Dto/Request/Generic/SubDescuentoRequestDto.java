package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import java.math.BigDecimal;

public record SubDescuentoRequestDto(
        String tipo, // $, %
        BigDecimal subDescuentoPorcentaje,
        BigDecimal montoSubdescuento
) {}

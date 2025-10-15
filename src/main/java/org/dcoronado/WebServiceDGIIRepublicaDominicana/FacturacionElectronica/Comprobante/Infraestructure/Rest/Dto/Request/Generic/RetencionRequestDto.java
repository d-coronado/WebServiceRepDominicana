package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import java.math.BigDecimal;

public record RetencionRequestDto(
        Integer indicadorRetencionPercepcion,
        BigDecimal montoITBISRetenido,
        BigDecimal montoRetencionRenta
) {}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import java.math.BigDecimal;

public record OtraMonedaDetalleItemRequestDto(
        BigDecimal precio,
        BigDecimal descuento,
        BigDecimal recargo,
        BigDecimal montoItem
) {}

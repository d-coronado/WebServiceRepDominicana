package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DescuentoORecargo(
        Integer numeroLinea,
        String tipoAjuste,
        Integer indicadorNorma1007,
        String descripcionDescuentoORecargo,
        String tipoValor,
        BigDecimal valorDescuentoORecargo,
        BigDecimal montoDescuentoORecargo,
        BigDecimal montoDescuentooRecargoOtraMoneda,
        Integer indicadorFacturacionDescuentoORecargo
) {

}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public final class DescuentoORecargo {
    private final Integer numeroLinea;
    private final String tipoAjuste;
    private final Integer indicadorNorma1007;
    private final String descripcionDescuentoORecargo;
    private final String tipoValor;
    private BigDecimal valorDescuentoORecargo;
    private BigDecimal montoDescuentoORecargo;
    private BigDecimal montoDescuentooRecargoOtraMoneda;
    private final Integer indicadorFacturacionDescuentoORecargo;


}

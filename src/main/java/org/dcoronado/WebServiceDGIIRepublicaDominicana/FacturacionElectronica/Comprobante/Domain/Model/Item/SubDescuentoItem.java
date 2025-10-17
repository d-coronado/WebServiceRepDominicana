package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SubDescuentoItem {
    private final String tipoSubDescuento;
    private BigDecimal subDescuentoPorcentaje;
    private BigDecimal montoSubDescuento;
}

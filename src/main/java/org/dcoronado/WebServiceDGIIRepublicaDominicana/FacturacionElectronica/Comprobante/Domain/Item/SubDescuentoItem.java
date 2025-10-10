package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class SubDescuentoItem {
    private final String tipoSubDescuento;
    private BigDecimal subDescuentoPorcentaje;
    private BigDecimal montoSubDescuento;
}

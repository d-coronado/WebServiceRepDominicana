package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class SubRecargoItem {
    private final String tipoSubRecargo;
    private BigDecimal subRecargoPorcentaje;
    private BigDecimal montoSubRecargo;
}

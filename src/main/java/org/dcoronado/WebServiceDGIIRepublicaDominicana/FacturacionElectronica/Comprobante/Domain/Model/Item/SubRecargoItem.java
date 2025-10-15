package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SubRecargoItem {
    private final String tipoSubRecargo;
    private BigDecimal subRecargoPorcentaje;
    private BigDecimal montoSubRecargo;
}

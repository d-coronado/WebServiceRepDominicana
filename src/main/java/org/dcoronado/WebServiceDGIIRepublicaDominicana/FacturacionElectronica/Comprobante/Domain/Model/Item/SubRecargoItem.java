package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SubRecargoItem {
    private final String tipoSubRecargo;
    private BigDecimal subRecargoPorcentaje;
    private BigDecimal montoSubRecargo;
}

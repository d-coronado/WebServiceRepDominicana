package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class RetencionItem {
    private final Integer indAgenteRetencionPercepcion;
    private BigDecimal montoITBSRetenido;
    private BigDecimal montoISRRetenido;
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtraMonedaDescuentoItem {
    private BigDecimal precioOtraMoneda;
    private BigDecimal descuentoOtraMoneda;
    private BigDecimal recargoOtraMoneda;
    private BigDecimal montoItemOtraMoneda;
}

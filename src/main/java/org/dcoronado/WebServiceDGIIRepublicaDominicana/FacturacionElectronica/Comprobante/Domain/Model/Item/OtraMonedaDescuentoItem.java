package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OtraMonedaDescuentoItem {
    private BigDecimal precioOtraMoneda;
    private BigDecimal descuentoOtraMoneda;
    private BigDecimal recargoOtraMoneda;
    private BigDecimal montoItemOtraMoneda;
}

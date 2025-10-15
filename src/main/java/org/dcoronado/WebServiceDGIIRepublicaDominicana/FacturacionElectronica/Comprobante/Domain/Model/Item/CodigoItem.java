package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item;

import lombok.Builder;

@Builder
public record CodigoItem(
        String tipoCodigo,
        String codigoItem
) {
}

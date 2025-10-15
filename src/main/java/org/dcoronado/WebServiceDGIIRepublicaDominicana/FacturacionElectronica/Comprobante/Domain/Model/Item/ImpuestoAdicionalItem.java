package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item;

import lombok.Builder;

@Builder
public record ImpuestoAdicionalItem(
        String tipoImpuestoAdicional
) {
}

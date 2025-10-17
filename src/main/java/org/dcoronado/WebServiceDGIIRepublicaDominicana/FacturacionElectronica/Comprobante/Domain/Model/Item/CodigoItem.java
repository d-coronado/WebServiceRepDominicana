package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class CodigoItem {
    private final String tipoCodigo;
    private final String codigoItem;
}

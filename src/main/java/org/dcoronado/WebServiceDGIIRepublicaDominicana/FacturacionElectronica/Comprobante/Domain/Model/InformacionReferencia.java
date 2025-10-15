package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;

@Builder
public record InformacionReferencia(
        String NCFModificado,
        String RNCOtroContribuyente,
        String fechaNCFModificado,
        Integer codigoModificacion,
        String razonModificado
) {
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain;

public record InformacionReferencia(
        String NCFModificado,
        String RNCOtroContribuyente,
        String fechaNCFModificado,
        Integer codigoModificacion,
        String razonModificado
) {
}

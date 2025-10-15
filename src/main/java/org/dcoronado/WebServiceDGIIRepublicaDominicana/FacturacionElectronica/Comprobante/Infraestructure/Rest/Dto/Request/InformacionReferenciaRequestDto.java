package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request;

public record InformacionReferenciaRequestDto(
        String NCFModificado,
        String RNCOtroContribuyente,
        String fechaNCFModificado,
        Integer codigoModificacion,
        String razonModificado
) {}

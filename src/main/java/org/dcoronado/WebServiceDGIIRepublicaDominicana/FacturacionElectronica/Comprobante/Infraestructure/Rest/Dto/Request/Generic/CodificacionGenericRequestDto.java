package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import jakarta.validation.constraints.Size;

public record CodificacionGenericRequestDto(
        @Size(max = 14, message = "{}")
        String tipoCodigo,

        @Size(max = 35, message = "{}")
        String codigoItem
) {}

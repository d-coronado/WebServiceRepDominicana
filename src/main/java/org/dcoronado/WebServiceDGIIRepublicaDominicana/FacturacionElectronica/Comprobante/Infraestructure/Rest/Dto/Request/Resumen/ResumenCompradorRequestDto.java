package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Resumen;

import jakarta.validation.constraints.Size;

public record ResumenCompradorRequestDto(
        @Size(min = 9, max = 11, message = "{}")
        String rnc,

        @Size(max = 20, message = "{}")
        String identificadorExtranjero,

        @Size(max = 150, message = "{}")
        String razonSocial
) {}

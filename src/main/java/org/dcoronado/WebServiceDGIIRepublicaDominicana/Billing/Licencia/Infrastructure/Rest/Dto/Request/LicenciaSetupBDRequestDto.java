package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public record LicenciaSetupBDRequestDto(
        @NotBlank String rnc,
        @NotBlank String host,
        @NotBlank String port
) {
}

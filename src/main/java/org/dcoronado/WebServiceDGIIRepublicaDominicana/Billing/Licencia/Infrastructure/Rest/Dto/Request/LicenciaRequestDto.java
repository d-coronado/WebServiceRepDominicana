package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

@Builder
public record LicenciaRequestDto(
        @NotBlank String rnc,
        @NotBlank String razonSocial,
        @NotBlank String direccionFiscal,
        String alias,
        String nombreContacto,
        String telefonoContacto,
        @NotNull AmbienteEnum ambiente
) {
}


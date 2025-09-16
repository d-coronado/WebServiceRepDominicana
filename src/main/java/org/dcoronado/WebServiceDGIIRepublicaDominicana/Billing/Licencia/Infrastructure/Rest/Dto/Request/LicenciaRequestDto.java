package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public record LicenciaRequestDto(
    @NotBlank String rnc,
    @NotBlank String razonSocial,
    @NotBlank String direccionFiscal,
    String alias,
    String nombreContacto,
    String telefonoContacto,
    @NotNull Ambiente ambiente
){}


package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public record SesionRequestDto (
         @NotBlank String rnc,
         @NotNull Ambiente ambiente
){ }

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto;

import jakarta.validation.constraints.NotBlank;

public record InfoTokenDgiiDto(
        @NotBlank String token,
        @NotBlank String fechaExpedido,
        @NotBlank String fechaExpira
) {}


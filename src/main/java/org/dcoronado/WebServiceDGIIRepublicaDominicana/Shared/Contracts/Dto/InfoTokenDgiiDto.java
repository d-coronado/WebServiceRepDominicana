package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto;

import jakarta.validation.constraints.NotBlank;

public record InfoTokenDgiiDto(
        @NotBlank String token,
        @NotBlank String fechaExpedido,
        @NotBlank String fechaExpira
) {
}


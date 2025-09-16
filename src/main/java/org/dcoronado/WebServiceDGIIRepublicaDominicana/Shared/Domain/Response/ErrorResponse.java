package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Response;


import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String exception,
        String message,
        String cause,
        LocalDateTime localDateTime
) {}
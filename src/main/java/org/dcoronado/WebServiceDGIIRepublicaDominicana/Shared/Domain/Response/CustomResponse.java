package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Response;

import java.time.LocalDateTime;

public record CustomResponse(
        int status,
        Object data,
        String message,
        LocalDateTime localDateTime
) {
}

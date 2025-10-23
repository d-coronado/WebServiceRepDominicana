package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response;

public record ResponseTokenDgii(
        String token,
        String expedido,
        String expira
) {
}

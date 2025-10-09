package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Response;

public record LicenciaResponseDto(
        Long id,
        String rncEmpresa,
        String razonSocial,
        String direccionFiscal,
        String alias,
        String nombreContacto,
        String telefonoContacto,
        String ambiente,
        Boolean isActive
) {
}

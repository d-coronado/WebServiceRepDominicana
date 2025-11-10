package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

public record LicenciaInfoDto(
        String rnc,
        String pathCertificado,
        String claveCertificado,
        AmbienteEnum limitAccessAmbiente,
        String razonSocial,
        String direccionFiscal
) {
}

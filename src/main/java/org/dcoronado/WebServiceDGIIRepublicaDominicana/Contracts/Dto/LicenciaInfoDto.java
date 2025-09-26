package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public record LicenciaInfoDto (
        String rnc,
        String pathCertificado,
        String claveCertificado,
        Ambiente limitAccessAmbiente
) {
}

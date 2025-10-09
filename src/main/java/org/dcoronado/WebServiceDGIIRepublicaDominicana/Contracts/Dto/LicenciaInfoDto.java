package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

public record LicenciaInfoDto (
        String rnc,
        String pathCertificado,
        String claveCertificado,
        AmbienteEnum limitAccessAmbiente
) {
}

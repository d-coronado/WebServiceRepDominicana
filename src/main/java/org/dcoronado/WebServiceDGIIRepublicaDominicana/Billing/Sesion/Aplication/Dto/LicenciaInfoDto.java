package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public record LicenciaInfoDto(
        String pathCertificado,
        String claveCertificado,
        Ambiente limitAccessAmbiente
){
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto.LicenciaInfoDto;

public interface LicenciaProviderPort {
    LicenciaInfoDto getLicencia(String rnc);
}

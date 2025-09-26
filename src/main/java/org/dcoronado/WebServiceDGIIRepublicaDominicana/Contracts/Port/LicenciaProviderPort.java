package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;

public interface LicenciaProviderPort {
    LicenciaInfoDto getLicenciaInfoByRnc(String rnc);
}

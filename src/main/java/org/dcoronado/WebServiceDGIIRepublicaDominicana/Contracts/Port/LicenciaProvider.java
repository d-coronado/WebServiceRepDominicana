package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;

public interface LicenciaProvider {
    LicenciaInfoDto getLicenciaInfoByRnc(String rnc);
}

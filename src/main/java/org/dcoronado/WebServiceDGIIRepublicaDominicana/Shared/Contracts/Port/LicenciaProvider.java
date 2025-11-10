package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.LicenciaInfoDto;

public interface LicenciaProvider {
    LicenciaInfoDto getLicenciaInfoByRnc(String rnc);
}

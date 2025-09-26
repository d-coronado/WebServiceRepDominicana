package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;

public interface LicenciaInfoPort {
    LicenciaInfoDto getLicenciaInfoByRnc(String rnc);
}

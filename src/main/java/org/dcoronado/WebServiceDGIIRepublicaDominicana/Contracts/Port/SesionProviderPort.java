package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.SesionInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

import java.util.Optional;

public interface SesionProviderPort {
    SesionInfoDto crear(String rnc, Ambiente ambiente) throws Exception;
    Optional<SesionInfoDto> obtenerSesionActiva(String rnc, Ambiente ambiente);
}

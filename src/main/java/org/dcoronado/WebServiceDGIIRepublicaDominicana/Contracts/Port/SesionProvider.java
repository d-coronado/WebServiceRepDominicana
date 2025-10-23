package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.SesionInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

import java.util.Optional;

public interface SesionProvider {
    SesionInfoDto crear(String rnc, AmbienteEnum ambiente) throws Exception;

    Optional<SesionInfoDto> obtenerSesionActiva(String rnc, AmbienteEnum ambiente);
}

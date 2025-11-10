package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.SesionInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

import java.util.Optional;

public interface SesionProvider {
    SesionInfoDto crear(String rnc, AmbienteEnum ambiente) throws Exception;

    Optional<SesionInfoDto> obtenerSesionActiva(String rnc, AmbienteEnum ambiente);
}

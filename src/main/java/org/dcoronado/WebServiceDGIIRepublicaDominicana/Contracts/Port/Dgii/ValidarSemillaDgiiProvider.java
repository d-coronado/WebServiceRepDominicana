package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.Dgii;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

public interface ValidarSemillaDgiiProvider {
    InfoTokenDgiiDto execute(AmbienteEnum ambiente, String documentConten);
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.Dgii;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

public interface ValidarSemillaDgiiProvider {
    InfoTokenDgiiDto execute(AmbienteEnum ambiente, String documentConten);
}

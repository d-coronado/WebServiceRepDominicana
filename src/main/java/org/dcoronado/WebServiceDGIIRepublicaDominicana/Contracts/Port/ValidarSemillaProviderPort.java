package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public interface ValidarSemillaProviderPort {
    InfoTokenDgiiDto execute(Ambiente ambiente, String documentConten);
}

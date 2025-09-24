package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public interface ValidarSemillaDgiiProviderPort {
    InfoTokenDgiiDto execute(Ambiente ambiente, String documentConten);
}

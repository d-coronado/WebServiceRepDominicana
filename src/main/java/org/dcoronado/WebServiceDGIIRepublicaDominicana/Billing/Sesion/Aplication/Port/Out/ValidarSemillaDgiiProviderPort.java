package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public interface ValidarSemillaDgiiProviderPort {
    String execute(Ambiente ambiente,String documentConten);
}

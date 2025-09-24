package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Domain.InfoTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public interface ValidarSemillaPort {
    InfoTokenDgii validarSemilla(Ambiente ambiente, String xmlSemilla);
}

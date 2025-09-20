package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public interface ValidarSemillaPort {
    String validarSemilla(Ambiente ambiente, String xmlSemilla);
}

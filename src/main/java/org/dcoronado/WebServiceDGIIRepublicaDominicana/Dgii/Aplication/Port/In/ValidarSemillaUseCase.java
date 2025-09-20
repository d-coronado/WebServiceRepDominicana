package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

public interface ValidarSemillaUseCase {
    String validarSemilla(Ambiente ambiente, String xmlSemilla);
}

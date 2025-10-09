package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Domain.InfoTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

public interface ValidarSemillaUseCase {
    InfoTokenDgii validarSemilla(AmbienteEnum ambiente, String xmlSemilla);
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

public interface ValidarSemillaDgiiUseCase {
    ResponseTokenDgii validarSemilla(AmbienteEnum ambiente, String xmlSemilla);
}

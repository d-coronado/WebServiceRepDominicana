package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

public interface ValidarSemillaDgiiPort {
    ResponseTokenDgii validarSemilla(AmbienteEnum ambiente, byte[] xmlSemilla);
}

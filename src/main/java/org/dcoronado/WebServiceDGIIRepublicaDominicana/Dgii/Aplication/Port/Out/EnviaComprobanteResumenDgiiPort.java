package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteResumenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

public interface EnviaComprobanteResumenDgiiPort {
    ResponseComprobanteResumenDgii execute(AmbienteEnum ambiente, String token, byte[] xmlComprobanteResumen);
}

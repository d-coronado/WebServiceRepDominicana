package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

public interface EnviaComprobanteDgiiUseCase {
    ResponseComprobanteDgii execute(AmbienteEnum ambiente, String token, byte[] xmlComprobante);
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

public interface EnviaComprobanteDgiiPort {
    ResponseComprobanteDgii execute(AmbienteEnum ambiente, String token, byte[] xmlComprobante);
}

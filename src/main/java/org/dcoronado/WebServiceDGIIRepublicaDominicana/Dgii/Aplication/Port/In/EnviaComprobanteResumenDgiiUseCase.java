package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteResumenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

public interface EnviaComprobanteResumenDgiiUseCase {
    ResponseComprobanteResumenDgii execute(AmbienteEnum ambiente, String token, byte[] xmlComprobante);
}

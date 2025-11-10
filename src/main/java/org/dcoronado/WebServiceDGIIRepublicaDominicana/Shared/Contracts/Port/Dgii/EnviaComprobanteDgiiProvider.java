package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.Dgii;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.InfoResponseComprobanteDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.InfoResponseComprobanteResumenDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

public interface EnviaComprobanteDgiiProvider {
    InfoResponseComprobanteDto enviaComprobanteProvider(AmbienteEnum ambienteEnum, String tokenDgii , byte[] xmlComprobante) throws Exception;
    InfoResponseComprobanteResumenDto enviaComprobanteResumenProvider(AmbienteEnum ambienteEnum, String tokenDgii, byte[] xmlComprobanteResumen) throws Exception;
}

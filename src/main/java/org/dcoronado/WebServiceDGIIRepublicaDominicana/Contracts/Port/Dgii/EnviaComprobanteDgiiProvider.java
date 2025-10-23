package org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.Dgii;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.InfoResponseComprobanteDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.InfoResponseComprobanteResumenDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

public interface EnviaComprobanteDgiiProvider {
    InfoResponseComprobanteDto enviaComprobanteProvider(AmbienteEnum ambienteEnum, String tokenDgii , byte[] xmlComprobante) throws Exception;
    InfoResponseComprobanteResumenDto enviaComprobanteResumenProvider(AmbienteEnum ambienteEnum, String tokenDgii, byte[] xmlComprobanteResumen) throws Exception;
}

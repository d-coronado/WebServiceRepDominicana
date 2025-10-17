package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;

public interface XsdValidatorPort {
    void execute(String xmlString, TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum, boolean esResumen) throws Exception;
}

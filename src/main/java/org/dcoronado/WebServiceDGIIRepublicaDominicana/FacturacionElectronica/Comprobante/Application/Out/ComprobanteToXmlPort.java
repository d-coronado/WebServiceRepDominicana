package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;

public interface ComprobanteToXmlPort {
    String toXmlExtendido(Comprobante comprobante);
    String toXmlResumido(Comprobante comprobante);
}

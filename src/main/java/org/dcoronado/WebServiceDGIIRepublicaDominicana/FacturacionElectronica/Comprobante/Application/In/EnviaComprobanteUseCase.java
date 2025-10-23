package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;

public interface EnviaComprobanteUseCase {
    Comprobante execute(Comprobante comprobante) throws Exception;
}

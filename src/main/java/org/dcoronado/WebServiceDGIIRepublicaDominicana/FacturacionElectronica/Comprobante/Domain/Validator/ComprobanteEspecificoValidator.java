package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;

public interface ComprobanteEspecificoValidator {
    void execute(Comprobante comprobante);
}

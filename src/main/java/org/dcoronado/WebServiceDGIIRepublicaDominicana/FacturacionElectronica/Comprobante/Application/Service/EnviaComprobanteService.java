package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In.EnviaComprobanteUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidator;
import org.springframework.stereotype.Service;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Service.ComprobanteValidatorFactory.getValidator;

@Service
@RequiredArgsConstructor
public class EnviaComprobanteService implements EnviaComprobanteUseCase {

    @Override
    public void execute(Comprobante comprobante) {

        ComprobanteValidator validator = getValidator(comprobante.tipoComprobanteTributarioEnum());
        validator.execute(comprobante);

    }
}

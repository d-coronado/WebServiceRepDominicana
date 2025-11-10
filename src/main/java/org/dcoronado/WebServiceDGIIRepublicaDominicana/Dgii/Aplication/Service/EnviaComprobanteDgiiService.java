package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.EnviaComprobanteDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.EnviaComprobanteDgiiPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnviaComprobanteDgiiService implements EnviaComprobanteDgiiUseCase {

    private final EnviaComprobanteDgiiPort enviaComprobantePort;

    @Override
    public ResponseComprobanteDgii execute(AmbienteEnum ambiente, String token, byte[] xmlComprobante) {
        if (xmlComprobante == null || xmlComprobante.length == 0) {
            throw new IllegalArgumentException("El archivo XML no puede ser nulo ni vacío");
        }
       return enviaComprobantePort.execute(ambiente, token, xmlComprobante);
    }

}

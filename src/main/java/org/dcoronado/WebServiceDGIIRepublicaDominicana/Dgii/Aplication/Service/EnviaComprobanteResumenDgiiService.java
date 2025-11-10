package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteResumenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.EnviaComprobanteResumenDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.EnviaComprobanteResumenDgiiPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnviaComprobanteResumenDgiiService implements EnviaComprobanteResumenDgiiUseCase {

    private final EnviaComprobanteResumenDgiiPort enviaComprobanteResumenPort;

    @Override
    public ResponseComprobanteResumenDgii execute(AmbienteEnum ambiente, String token, byte[] xmlComprobante) {
        return enviaComprobanteResumenPort.execute(ambiente, token, xmlComprobante);
    }
}

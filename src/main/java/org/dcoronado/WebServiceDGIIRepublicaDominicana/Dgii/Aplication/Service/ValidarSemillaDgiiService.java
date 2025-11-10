package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ValidarSemillaDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.ValidarSemillaDgiiPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ValidarSemillaDgiiService implements ValidarSemillaDgiiUseCase {

    private final ValidarSemillaDgiiPort validarSemillaPort;

    @Override
    public ResponseTokenDgii validarSemilla(AmbienteEnum ambiente, String xmlSemilla) {
        return validarSemillaPort.validarSemilla(ambiente,xmlSemilla.getBytes(StandardCharsets.UTF_8));
    }
}

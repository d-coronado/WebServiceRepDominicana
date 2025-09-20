package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ValidarSemillaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.ValidarSemillaPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidarSemillaService implements ValidarSemillaUseCase {

    private final ValidarSemillaPort validarSemillaPort;

    @Override
    public String validarSemilla(Ambiente ambiente, String xmlSemilla) {
        return validarSemillaPort.validarSemilla(ambiente, xmlSemilla);
    }
}

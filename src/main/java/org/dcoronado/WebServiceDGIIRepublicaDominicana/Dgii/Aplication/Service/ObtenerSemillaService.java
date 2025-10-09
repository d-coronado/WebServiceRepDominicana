package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ObtenerSemillaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.ObtenerSemillaPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObtenerSemillaService implements ObtenerSemillaUseCase {

    private final ObtenerSemillaPort obtenerSemillaPort;

    @Override
    public String obtenerSemilla(AmbienteEnum ambiente) {
        return obtenerSemillaPort.execute(ambiente);
    }
}

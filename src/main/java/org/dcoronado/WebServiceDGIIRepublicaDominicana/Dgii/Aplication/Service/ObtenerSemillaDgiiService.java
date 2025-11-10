package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ObtenerSemillaDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.ObtenerSemillaDgiiPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObtenerSemillaDgiiService implements ObtenerSemillaDgiiUseCase {

    private final ObtenerSemillaDgiiPort obtenerSemillaPort;

    @Override
    public String obtenerSemilla(AmbienteEnum ambiente) {
        return obtenerSemillaPort.execute(ambiente);
    }
}

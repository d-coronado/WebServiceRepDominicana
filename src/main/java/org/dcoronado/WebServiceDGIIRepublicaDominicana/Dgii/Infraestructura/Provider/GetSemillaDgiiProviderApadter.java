package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Provider;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.Dgii.GetSemillaDgiiProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ObtenerSemillaDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSemillaDgiiProviderApadter implements GetSemillaDgiiProvider {

    private final ObtenerSemillaDgiiUseCase obtenerSemillaUseCase;

    @Override
    public String execute(AmbienteEnum ambiente) {
        return obtenerSemillaUseCase.obtenerSemilla(ambiente);
    }
}

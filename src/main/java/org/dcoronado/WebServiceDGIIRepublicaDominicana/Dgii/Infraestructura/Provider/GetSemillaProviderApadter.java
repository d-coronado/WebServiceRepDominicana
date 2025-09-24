package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Provider;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.GetSemillaDgiiProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ObtenerSemillaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSemillaProviderApadter implements GetSemillaDgiiProviderPort {

    private final ObtenerSemillaUseCase obtenerSemillaUseCase;

    @Override
    public String execute(Ambiente ambiente) {
        return obtenerSemillaUseCase.obtenerSemilla(ambiente);
    }
}

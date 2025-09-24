package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Provider;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.ValidarSemillaDgiiProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ValidarSemillaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidarSemillaProviderAdapter implements ValidarSemillaDgiiProviderPort {

    private final ValidarSemillaUseCase validarSemillaUseCase;

    @Override
    public String execute(Ambiente ambiente, String documentConten) {
        return validarSemillaUseCase.validarSemilla(ambiente, documentConten);
    }
}

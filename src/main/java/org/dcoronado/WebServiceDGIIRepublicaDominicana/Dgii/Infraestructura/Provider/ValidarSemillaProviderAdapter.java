package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Provider;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.ValidarSemillaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ValidarSemillaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Domain.InfoTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidarSemillaProviderAdapter implements ValidarSemillaProviderPort {

    private final ValidarSemillaUseCase validarSemillaUseCase;

    @Override
    public InfoTokenDgiiDto execute(Ambiente ambiente, String documentConten) {
        InfoTokenDgii result = validarSemillaUseCase.validarSemilla(ambiente, documentConten);
        return new InfoTokenDgiiDto(
                result.token(),
                result.expedido(),
                result.expira()
        );
    }
}

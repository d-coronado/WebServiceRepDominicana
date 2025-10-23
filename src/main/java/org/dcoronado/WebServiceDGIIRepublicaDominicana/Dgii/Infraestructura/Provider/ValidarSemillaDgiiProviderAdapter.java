package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Provider;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.Dgii.ValidarSemillaDgiiProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ValidarSemillaDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidarSemillaDgiiProviderAdapter implements ValidarSemillaDgiiProvider {

    private final ValidarSemillaDgiiUseCase validarSemillaUseCase;

    @Override
    public InfoTokenDgiiDto execute(AmbienteEnum ambiente, String documentConten) {
        ResponseTokenDgii result = validarSemillaUseCase.validarSemilla(ambiente, documentConten);
        return new InfoTokenDgiiDto(
                result.token(),
                result.expedido(),
                result.expira()
        );
    }
}

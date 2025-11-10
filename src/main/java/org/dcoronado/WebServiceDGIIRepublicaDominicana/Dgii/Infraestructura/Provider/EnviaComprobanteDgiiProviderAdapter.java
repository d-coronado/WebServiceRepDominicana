package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Provider;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.InfoResponseComprobanteDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.InfoResponseComprobanteResumenDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.Dgii.EnviaComprobanteDgiiProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteResumenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.EnviaComprobanteResumenDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.EnviaComprobanteDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EnviaComprobanteDgiiProviderAdapter implements EnviaComprobanteDgiiProvider {

    private final EnviaComprobanteDgiiUseCase enviaComprobanteUseCase;
    private final EnviaComprobanteResumenDgiiUseCase enviaComprobanteResumenUseCase;

    @Override
    public InfoResponseComprobanteDto enviaComprobanteProvider(AmbienteEnum ambienteEnum, String tokenDgii, byte[] xmlComprobante){
        ResponseComprobanteDgii responseDgii = enviaComprobanteUseCase.execute(ambienteEnum,tokenDgii, xmlComprobante);
        return new InfoResponseComprobanteDto(
                responseDgii.trackId(),
                responseDgii.error(),
                responseDgii.mensaje()
        );
    }

    @Override
    public InfoResponseComprobanteResumenDto enviaComprobanteResumenProvider(AmbienteEnum ambienteEnum, String tokenDgii, byte[] xmlComprobanteResumen){
      ResponseComprobanteResumenDgii resumenDgii =  enviaComprobanteResumenUseCase.execute(ambienteEnum,tokenDgii, xmlComprobanteResumen);
      return new InfoResponseComprobanteResumenDto(
              resumenDgii.codigo(),
              resumenDgii.estado(),
              Optional.ofNullable(resumenDgii.mensajes())
                      .orElse(List.of()) // crea lista vacía si es null
                      .stream()
                      .map(m -> new InfoResponseComprobanteResumenDto.Mensaje(m.codigo(), m.valor()))
                      .toList(),
              resumenDgii.encf(),
              resumenDgii.secuenciaUtilizada()
      );
    }
}

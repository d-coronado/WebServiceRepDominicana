package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.Cient.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.ValidarSemillaPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.DgiiEnviroments;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Domain.InfoTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.http.RestClientUtil;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidarSemillaRestClientAdapter implements ValidarSemillaPort {

    private final RestClientUtil restClientUtil;
    private final DgiiEnviroments dgiiEnviroments;

    @Override
    public InfoTokenDgii validarSemilla(AmbienteEnum ambiente, String xmlSemilla) {
        String url = dgiiEnviroments.getValidarSemillaUrl(ambiente);
        return restClientUtil.postFile(url,"xml", "semillaFirmada.xml",xmlSemilla,MediaType.TEXT_XML, InfoTokenDgii.class);
    }
}

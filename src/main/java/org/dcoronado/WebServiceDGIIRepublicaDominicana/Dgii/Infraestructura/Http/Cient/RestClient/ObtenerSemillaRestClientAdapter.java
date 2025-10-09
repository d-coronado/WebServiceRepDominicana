package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.Cient.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.ObtenerSemillaPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.DgiiEnviroments;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.http.RestClientUtil;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObtenerSemillaRestClientAdapter implements ObtenerSemillaPort {

    private final RestClientUtil restClientUtil;
    private final DgiiEnviroments dgiiEnviroments;

    @Override
    public String execute(AmbienteEnum ambiente) {
        String url = dgiiEnviroments.getGenerarSemillaUrl(ambiente);
        return restClientUtil.get(url, String.class);
    }
}

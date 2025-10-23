package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.Cient.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.ObtenerSemillaDgiiPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.DgiiEnvironments;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObtenerSemillaDgiiRestClientAdapter implements ObtenerSemillaDgiiPort {

    private final RestClient.Builder restClientBuilder;
    private final DgiiEnvironments dgiiEnviroments;

    @Override
    public String execute(AmbienteEnum ambiente) {
        RestClient restClient = restClientBuilder.build();

        try {
            return restClient.get()
                    .uri(dgiiEnviroments.getGenerarSemillaEndpoint(ambiente))
                    .header("Accept", "*/*")
                    .retrieve()
                    .body(String.class);

        }catch (Exception e){
            log.error("Error obteniendo semilla DGII", e);
            throw new RuntimeException("DGII request failed", e);
        }
    }
}

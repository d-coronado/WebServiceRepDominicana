package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.Cient.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.EnviaComprobanteDgiiPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.DgiiEnvironments;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.DgiiEnvironments.BEARER_TOKEN_FORMAT;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnviaComprobanteDgiiRestClientAdapter implements EnviaComprobanteDgiiPort {

    private final RestClient.Builder restClientBuilder;
    private final DgiiEnvironments dgiiEnvironments;

    @Override
    public ResponseComprobanteDgii execute(AmbienteEnum ambiente, String token, byte[] xmlComprobante) {


        RestClient restClient = restClientBuilder
                .requestInterceptor((request, body, execution) ->
                        execution.execute(request, body))
                .build();

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("xml", new ByteArrayResource(xmlComprobante) {
                    @Override
                    public String getFilename() {
                        return "comprobante.xml";
                    }
                })
                .contentType(MediaType.TEXT_XML);

        try {
            return restClient.post()
                    .uri(dgiiEnvironments.getEnviaComprobanteEndpoint(ambiente))
                    .header("Authorization", BEARER_TOKEN_FORMAT.concat(token))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(bodyBuilder.build())
                    .retrieve()
                    .body(ResponseComprobanteDgii.class);

        } catch (Exception e) {
            log.error("Error enviando comprobante DGII", e);
            throw new RuntimeException("DGII request failed", e);
        }
    }
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.Cient.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseComprobanteResumenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.EnviaComprobanteResumenDgiiPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.DgiiEnvironments;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.DgiiEnvironments.BEARER_TOKEN_FORMAT;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnviaComprobanteResumenDgiiRestClientAdapter implements EnviaComprobanteResumenDgiiPort {


    private final RestClient.Builder restClientBuilder;
    private final DgiiEnvironments dgiiEnvironments;

    @Override
    public ResponseComprobanteResumenDgii execute(AmbienteEnum ambiente, String token, byte[] xmlComprobanteResumen) {

        RestClient restClient = restClientBuilder
                .requestInterceptor((request, body, execution) ->
                        execution.execute(request, body))
                .build();

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder
                .part("xml", new ByteArrayResource(xmlComprobanteResumen) {
                    @Override
                    public String getFilename() {
                        return "comprobante-resumen.xml";
                    }
                })
                .contentType(MediaType.TEXT_XML);

        try {
            return restClient.post()
                    .uri(dgiiEnvironments.getEnviaComprobanteResumenEndpoint(ambiente))
                    .header("Authorization", BEARER_TOKEN_FORMAT.concat(token))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(bodyBuilder.build())
                    .retrieve()
                    .body(ResponseComprobanteResumenDgii.class);

        } catch (Exception e) {
            log.error("Error enviando comprobante DGII", e);
            throw new RuntimeException("DGII request failed", e);
        }
    }
}

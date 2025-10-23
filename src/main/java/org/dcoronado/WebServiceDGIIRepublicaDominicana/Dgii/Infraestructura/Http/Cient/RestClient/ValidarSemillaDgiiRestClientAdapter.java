package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.Cient.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.Out.ValidarSemillaDgiiPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Infraestructura.Http.DgiiEnvironments;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Dto.Response.ResponseTokenDgii;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidarSemillaDgiiRestClientAdapter implements ValidarSemillaDgiiPort {

    private final RestClient.Builder restClientBuilder;
    private final DgiiEnvironments dgiiEnvironments;

    @Override
    public ResponseTokenDgii validarSemilla(AmbienteEnum ambiente, byte[] xmlSemillaFirmada) {
        RestClient restClient = restClientBuilder.build();

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder
                .part("xml", new ByteArrayResource(xmlSemillaFirmada){
                        @Override
                        public String getFilename() {
                            return "semillaFirmada.xml";
                        }})
                .contentType(MediaType.TEXT_XML);

        try {
            return restClient.post()
                    .uri(dgiiEnvironments.getValidarSemillaEndpoint(ambiente))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(bodyBuilder.build())
                    .retrieve()
                    .body(ResponseTokenDgii.class);

        } catch (Exception e) {
            log.error("Error enviando comprobante DGII", e);
            throw new RuntimeException("DGII request failed", e);
        }
    }

}

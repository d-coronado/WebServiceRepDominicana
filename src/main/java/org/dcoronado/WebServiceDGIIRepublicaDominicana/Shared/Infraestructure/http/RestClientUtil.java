package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class RestClientUtil {

    private final RestClient restClient;

    public RestClientUtil() {
        this.restClient = RestClient.builder().build();
    }


    public <T> T get(String url, Class<T> responseType) {
        try {
            return restClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .body(responseType);

        } catch (Exception e) {
            log.error("Error en GET a {}: {}", url, e.getMessage());
            throw new RuntimeException("Error en GET: " + e.getMessage(), e);
        }
    }

    /**
     * POST con JSON
     */
    public <T> T post(String url, Object body, Class<T> responseType) {
        try {
            return restClient
                    .post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(responseType);

        } catch (Exception e) {
            log.error("Error en POST a {}: {}", url, e.getMessage());
            throw new RuntimeException("Error en POST: " + e.getMessage(), e);
        }
    }


    public <T> T postFile(String url, String fieldName, String fileName,
                          String content, MediaType mediaType, Class<T> responseType) {

        ByteArrayResource resource = new ByteArrayResource(content.getBytes()) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };

        // construir el multipart con Spring
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part(fieldName, resource)
                .filename(fileName)
                .contentType(mediaType);

        return restClient.post()
                .uri(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(bodyBuilder.build())
                .retrieve()
                .body(responseType);
    }


}

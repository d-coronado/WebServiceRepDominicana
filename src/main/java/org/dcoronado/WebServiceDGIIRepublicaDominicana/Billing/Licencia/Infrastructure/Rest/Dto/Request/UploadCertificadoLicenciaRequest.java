package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record UploadCertificadoLicenciaRequest(
        @NotBlank
        String rnc,
        MultipartFile archivo,
        @NotBlank
        String password
) {
}

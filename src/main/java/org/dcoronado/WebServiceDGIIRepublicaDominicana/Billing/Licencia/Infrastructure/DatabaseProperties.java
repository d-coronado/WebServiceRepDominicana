package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties(prefix = "app.database")
public class DatabaseProperties {
    @NotBlank(message = "El host de la base de datos no puede estar vacío")
    private String host;

    @NotBlank(message = "El puerto de la base de datos no puede estar vacío")
    @Pattern(regexp = "\\d+", message = "El puerto de la base de datos debe ser un número")
    private String port;
}

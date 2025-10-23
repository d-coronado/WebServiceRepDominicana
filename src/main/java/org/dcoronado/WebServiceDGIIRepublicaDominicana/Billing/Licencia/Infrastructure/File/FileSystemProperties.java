package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.File;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.getCurrentOS;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "licencia.filesystem.basepath")
public class FileSystemProperties {

    private String windows;
    private String linux;
    private String mac;

    public String getBasePathByCurrentOS() {
        String basePath;
        switch (getCurrentOS()) {
            case WINDOWS -> basePath = windows;
            case MAC -> basePath = mac;
            case LINUX -> basePath = linux;
            default -> throw new IllegalStateException("SO no soportado");
        }
        ;
        notBlank(basePath, "Debes definir el basepath para tu SO :" + getCurrentOS().toString());
        return basePath;
    }

}
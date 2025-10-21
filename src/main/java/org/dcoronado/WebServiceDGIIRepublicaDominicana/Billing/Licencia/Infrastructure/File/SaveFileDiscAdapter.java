package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.File;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.SaveFilePort;
import org.springframework.stereotype.Component;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Utils.FileSiytemDiscUtil.*;


@Component
@RequiredArgsConstructor
public class SaveFileDiscAdapter implements SaveFilePort {

    private final FileSystemProperties fileSystemProperties;

    @Override
    public String save(String path, byte[] content) {
        String pathAbsolute = fileSystemProperties.getBasePathByCurrentOS() + path;
        /* Guarda el archivo en disc */
        store(pathAbsolute, content);
        return pathAbsolute;
    }
}

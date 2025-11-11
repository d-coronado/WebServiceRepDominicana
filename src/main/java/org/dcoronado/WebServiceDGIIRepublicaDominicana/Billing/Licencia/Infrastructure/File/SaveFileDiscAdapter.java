package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.File;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.SaveFilePort;
import org.springframework.stereotype.Component;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.Util.FileSiytemDiscUtil.*;


@Component
@RequiredArgsConstructor
public class SaveFileDiscAdapter implements SaveFilePort {

    private final FileSystemProperties fileSystemProperties;

    @Override
    public String getBasePath() {
        return fileSystemProperties.getBasepath();
    }

    @Override
    public void save(String path, byte[] content) {
        /* Guarda el archivo en disc */
        store(path, content);
    }

}


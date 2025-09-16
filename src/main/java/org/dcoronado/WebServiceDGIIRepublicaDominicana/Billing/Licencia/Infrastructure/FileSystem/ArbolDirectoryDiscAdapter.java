package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.FileSystem;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.DirectoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioNode;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class ArbolDirectoryDiscAdapter implements DirectoryPort {

    private final FileSystemProperties fileSystemProperties;

    @Override
    public void createDirectory(DirectorioNode estructura) {
        Path basePath = Paths.get(fileSystemProperties.getBasePathByCurrentOS());
        ArbolDirectorioCreatorDisc creador = new ArbolDirectorioCreatorDisc(basePath);
        creador.crearEstructuraDisc(estructura);
    }
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Utils;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileSiytemDiscUtil {

    private FileSiytemDiscUtil() {}

    public static void store(String path, byte[] content) {
        try {
            Path target = Paths.get(path);
            Files.createDirectories(target.getParent());
            Files.write(target, content);
        } catch (IOException e) {
            throw new InfrastructureException("Error guardando archivo", e);
        }
    }

    public static byte[] readBytes(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new InfrastructureException("Error leyendo archivo", e);
        }
    }

    public static void deleteFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            throw new InfrastructureException("Error eliminando archivo", e);
        }
    }
}

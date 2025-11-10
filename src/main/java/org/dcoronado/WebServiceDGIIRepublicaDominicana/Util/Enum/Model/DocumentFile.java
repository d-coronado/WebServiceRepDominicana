package org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Model;

import lombok.Getter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.safeTrim;

@Getter
public class DocumentFile {

    private final String nombre;
    private final byte[] contenido;

    // Constructor principal (validación estricta)
    private DocumentFile(String nombre, byte[] contenido, boolean skipValidation) {
        if (!skipValidation) {
            validateArchivo(nombre, contenido);
        }
        this.nombre = safeTrim(nombre);
        this.contenido = contenido;
    }

    // Fábrica de negocio
    public static DocumentFile of(String nombre, byte[] contenido) {
        return new DocumentFile(nombre, contenido, false);
    }

    // Fábrica de reconstrucción desde BD (contenido puede ser null)
    public static DocumentFile reconstruir(String nombre) {
        return new DocumentFile(nombre, null, true);
    }

    // Validación de extensiones permitidas
    public void validateExtension(String... extensionesPermitidas) {
        String extension = getExtension(nombre);
        if (Arrays.stream(extensionesPermitidas)
                .noneMatch(e -> e.equalsIgnoreCase(extension))) {
            throw new InvalidArgumentException(
                    "Extensión '%s' no permitida. Permitidas: %s"
                            .formatted(extension, String.join(",", extensionesPermitidas))
            );
        }
    }

    public String getExtension(String nombre) {
        int idx = nombre.lastIndexOf('.');
        if (idx == -1) return "";
        return nombre.substring(idx + 1);
    }

    public String asText() {
        if (contenido == null) return null;
        return new String(contenido, StandardCharsets.UTF_8);
    }

    // Validación estricta
    private static void validateArchivo(String nombreDocumento, byte[] archivo) {
        notBlank(nombreDocumento, "Nombre Documento requerido");
        if (!nombreDocumento.matches("[a-zA-Z0-9._\\- ()]+"))
            throw new InvalidArgumentException("Nombre de archivo contiene caracteres inválidos");
        if (archivo == null || archivo.length == 0) throw new InvalidArgumentException("Archivo no puede estar vacío");
    }
}

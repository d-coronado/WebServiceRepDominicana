package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;

import java.util.List;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public class ArchivoValidator {

    /* Solo se aceptaran archivos con extension .xml */
    public static void validate( String nombreDocumento, byte[] archivo) {
        notBlank(nombreDocumento,"Nombre Documento required");
        if (!nombreDocumento.matches("[a-zA-Z0-9._-]+"))
            throw new InvalidArgumentException("Nombre de archivo contiene caracteres inválidos");
        if (archivo.length == 0) throw new InvalidArgumentException ("Archivo no puede estar vacío");
    }

    /* Solo se aceptaran archivos con extension .xml */
    public static void validateExtensionXml(String nombreDocumento) {
        List<String> extensionesPermitidas = List.of("xml");
        String extension = nombreDocumento.contains(".")
                ? nombreDocumento.substring(nombreDocumento.lastIndexOf('.') + 1).toLowerCase()
                : "";
        if (!extensionesPermitidas.contains(extension))
            throw new InvalidArgumentException("Extensión no permitida, se requiere .xml");
    }
}

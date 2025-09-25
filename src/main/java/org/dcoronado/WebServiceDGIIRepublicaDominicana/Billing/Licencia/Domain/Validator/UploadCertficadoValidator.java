package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;

import java.io.IOException;
import java.io.InputStream;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public class UploadCertficadoValidator {
    public static void validar(String rnc, String nombreArchivo, String password, InputStream archivo) throws IOException {
        notBlank(rnc, "rnc required");
        notBlank(nombreArchivo, "nombreArchivo required");
        notBlank(password, "password required");
        byte[] archivoBytes = archivo.readAllBytes();
        if (archivoBytes.length == 0)  throw new InvalidArgumentException("El archivo no puede estar vacío");
    }
}

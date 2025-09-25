package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import java.io.IOException;
import java.io.InputStream;

public interface UploadCertificadoUseCase {

    /**
     * Ejecuta la subida de un certificado.
     *
     * @param rnc      RNC del contribuyente a validar.
     * @param archivo  Contenido del certificado.
     * @param password Contraseña para el certificado
     */
    void execute(String rnc, InputStream archivo, String nombreArchivo, String password) throws IOException;
}

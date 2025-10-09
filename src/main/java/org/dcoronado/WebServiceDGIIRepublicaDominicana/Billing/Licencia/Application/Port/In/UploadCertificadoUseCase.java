package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import java.io.IOException;

public interface UploadCertificadoUseCase {
    void execute(String rnc, String nombreArchivo, byte[] archivo, String password) throws IOException;
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import java.io.InputStream;

public interface FirmarDocumentUseCase {
    String firmarDocumentByLicencia(String rnc, InputStream archivo) throws Exception;
}

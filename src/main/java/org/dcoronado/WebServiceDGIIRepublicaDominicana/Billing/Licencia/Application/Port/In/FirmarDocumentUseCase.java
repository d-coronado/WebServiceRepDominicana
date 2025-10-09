package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

public interface FirmarDocumentUseCase {
    String firmarDocumentByLicencia(String rnc, String nombreArchivo, byte[] contenidoArchivo) throws Exception;
}

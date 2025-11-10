package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.UploadCertificadoDigitalCommand;

import java.io.IOException;

public interface UploadCertificadoUseCase {
    void execute(UploadCertificadoDigitalCommand command) throws IOException;
}

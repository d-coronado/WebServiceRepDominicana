package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.FirmarDocumentoCommand;

public interface FirmarDocumentUseCase {
    String firmarDocumentByLicencia(FirmarDocumentoCommand command) throws Exception;
}

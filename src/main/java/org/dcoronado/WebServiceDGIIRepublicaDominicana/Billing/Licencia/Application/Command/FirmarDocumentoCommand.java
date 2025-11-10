package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public record FirmarDocumentoCommand(
        String rnc,
        String nombreDocumento,
        byte[] documento
) {
    public FirmarDocumentoCommand {
        notBlank(rnc, "RNC required");
        notBlank(nombreDocumento, "nombreDocumento required");
    }
}

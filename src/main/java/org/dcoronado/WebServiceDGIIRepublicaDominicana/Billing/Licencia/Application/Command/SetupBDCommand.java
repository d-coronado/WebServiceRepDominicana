package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public record SetupBDCommand(
        String rnc,
        String host,
        String puerto
) {
    public SetupBDCommand {
        notBlank(rnc, "RNC required");
        notBlank(host, "Host requerido");
        notBlank(puerto, "Puerto requerido");
    }
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public record UploadCertificadoDigitalCommand(
        String rnc,
        String nombreCertificado,
        byte[] certificadoDigitalContenido,
        String claveCertificado
) {
    public UploadCertificadoDigitalCommand {
        notBlank(rnc,"RNC required");
        notBlank(nombreCertificado,"nombreCertificado required");
        notBlank(claveCertificado,"claveCertificado required");
    }
}

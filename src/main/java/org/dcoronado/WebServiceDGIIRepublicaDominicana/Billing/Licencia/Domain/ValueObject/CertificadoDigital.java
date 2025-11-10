package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Model.DocumentFile;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Getter
@EqualsAndHashCode
public class CertificadoDigital {

    private final DocumentFile certificado;
    private final String clave;
    private final String rutaCertificado;

    private CertificadoDigital(DocumentFile certificado, String clave, String rutaCertificado, boolean skipValidation) {
        if (!skipValidation) {
            if (certificado == null) throw new InvalidArgumentException("Certificado requerido");
            notBlank(clave, "Clave del certificado requerida");
            certificado.validateExtension("p12");
        }
        this.certificado = certificado;
        this.clave = clave;
        this.rutaCertificado = rutaCertificado;
    }

    // Fábrica de negocio
    public static CertificadoDigital crear(DocumentFile documentFile, String clave) {
        return new CertificadoDigital(documentFile, clave, null, false);
    }

    // Para actualizar ruta (inmutable)
    public CertificadoDigital conRuta(String rutaCertificado) {
        notBlank(rutaCertificado, "Ruta del certificado requerida");
        return new CertificadoDigital(this.certificado, this.clave, rutaCertificado, false);
    }

    // Fábrica de reconstrucción desde BD
    public static CertificadoDigital reconstruirDesdeBD(DocumentFile documentFile, String clave, String rutaCertificado) {
        return new CertificadoDigital(documentFile, clave, rutaCertificado, true);
    }

    public boolean estaListoParaFirmar() {
        return rutaCertificado != null && clave != null;
    }
}

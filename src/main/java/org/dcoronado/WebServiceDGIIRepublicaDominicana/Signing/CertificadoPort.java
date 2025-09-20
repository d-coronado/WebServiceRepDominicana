package org.dcoronado.WebServiceDGIIRepublicaDominicana.Signing;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Signing.Domain.KeyAndCertificate;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public interface CertificadoPort {

    /**
     * Devuelve la clave privada y el certificado a partir de una ruta y contraseña.
     */
    KeyAndCertificate loadCertificado(String filePath, String password) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException;
}

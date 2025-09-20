package org.dcoronado.WebServiceDGIIRepublicaDominicana.Signing;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public interface SignXmlUseCase {

    String signXml(String xmlContent, String certificadoPath, String password) throws CertificateException, UnrecoverableEntryException, KeyStoreException, IOException, NoSuchAlgorithmException, ParserConfigurationException, TransformerException;
}

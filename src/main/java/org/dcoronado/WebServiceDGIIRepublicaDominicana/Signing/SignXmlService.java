package org.dcoronado.WebServiceDGIIRepublicaDominicana.Signing;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Signing.Domain.KeyAndCertificate;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Signing.Domain.XmlSigner;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import static java.util.Objects.isNull;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Signing.Domain.XmlSigner.convertirStringAXML;

@Service
@RequiredArgsConstructor
public class SignXmlService implements SignXmlUseCase{

    private final CertificadoPort certificadoPort;

    @Override
    public String signXml(String xmlContent, String certificadoPath, String password) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, ParserConfigurationException, TransformerException {
        if(isNull(xmlContent)) throw new InvalidArgumentException("XML content cannot be null");
        InputStream xmlStream = new ByteArrayInputStream(xmlContent.getBytes());
        KeyAndCertificate keyCert = certificadoPort.loadCertificado(certificadoPath, password);
        DOMSignContext xmlFirmado = XmlSigner.signXml(xmlStream, keyCert.privateKey, keyCert.certificate);
        return convertirStringAXML(xmlFirmado);
    }
}

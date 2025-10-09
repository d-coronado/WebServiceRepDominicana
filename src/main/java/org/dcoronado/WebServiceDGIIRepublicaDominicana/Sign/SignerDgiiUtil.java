package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign;

import oracle.xml.parser.v2.DOMParser;
import oracle.xml.parser.v2.XMLDocument;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignerDgiiUtil {

    private static final String METODO_FIRMADO = "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";

    public static DOMSignContext sign(InputStream archivoXML, KeyStore.PrivateKeyEntry keyEntry) throws InfrastructureException {
        try {
            // Creamos un DOM XMLSignatureFactory
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            Reference ref = fac.newReference(
                    "",
                    fac.newDigestMethod(DigestMethod.SHA256, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                    null,
                    null
            );

            SignedInfo si = fac.newSignedInfo(
                    fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(METODO_FIRMADO, null),
                    Collections.singletonList(ref)
            );

            // Obtenemos el certificado desde la KeyEntry
            X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
            KeyInfoFactory kinfoFactory = fac.getKeyInfoFactory();
            List<Serializable> x509Content = new ArrayList<>();
            x509Content.add(cert);
            X509Data x509d = kinfoFactory.newX509Data(x509Content);
            KeyInfo kinfo = kinfoFactory.newKeyInfo(Collections.singletonList(x509d));

            // Parseamos el XML usando DOMParser de Oracle
            DOMParser parser = new DOMParser();
            parser.setPreserveWhitespace(false);
            parser.parse(archivoXML);
            XMLDocument xml = parser.getDocument();
            Element xmlRoot = xml.getDocumentElement();

            // Creamos el contexto de firma
            DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), xmlRoot);

            // Creamos la firma
            XMLSignature signature = fac.newXMLSignature(si, kinfo);

            // Firmamos
            signature.sign(dsc);

            return dsc;

        } catch (Exception e) {
            throw new InfrastructureException("OCURRIO UN ERROR DURANTE LA FIRMA: " + e.toString(), e);
        }
    }

    /**
     * Firma un XML pasando la clave privada y el certificado directamente.
     */
    public static DOMSignContext sign(InputStream archivoXML, PrivateKey privateKey, X509Certificate certificate) throws InfrastructureException {
        try {
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            Reference ref = fac.newReference(
                    "",
                    fac.newDigestMethod(DigestMethod.SHA256, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                    null,
                    null
            );

            SignedInfo si = fac.newSignedInfo(
                    fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(METODO_FIRMADO, null),
                    Collections.singletonList(ref)
            );

            // KeyInfo con el certificado pasado
            KeyInfoFactory kinfoFactory = fac.getKeyInfoFactory();
            List<Serializable> x509Content = new ArrayList<>();
            x509Content.add(certificate);
            X509Data x509d = kinfoFactory.newX509Data(x509Content);
            KeyInfo kinfo = kinfoFactory.newKeyInfo(Collections.singletonList(x509d));

            // Parseamos el XML usando DOMParser de Oracle
            DOMParser parser = new DOMParser();
            parser.setPreserveWhitespace(false);
            parser.parse(archivoXML);
            XMLDocument xml = parser.getDocument();
            Element xmlRoot = xml.getDocumentElement();

            // Contexto de firma usando la clave privada
            DOMSignContext dsc = new DOMSignContext(privateKey, xmlRoot);

            XMLSignature signature = fac.newXMLSignature(si, kinfo);
            signature.sign(dsc);

            return dsc;

        } catch (Exception e) {
            throw new InfrastructureException("OCURRIO UN ERROR DURANTE LA FIRMA: " + e.toString(), e);
        }
    }


    public static String convertirStringAXML(final DOMSignContext dsc) throws ParserConfigurationException, TransformerException {
        Element signedXmlRoot = (Element) dsc.getParent();
        Document newXmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        newXmlDoc.appendChild(newXmlDoc.importNode(signedXmlRoot, true));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StringWriter buffer = new StringWriter();
        transformer.transform(new DOMSource(newXmlDoc), new StreamResult(buffer));

        return buffer.toString();
    }

    public static InputStream stringToInputStream(String xml) {
        return new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
    }


}

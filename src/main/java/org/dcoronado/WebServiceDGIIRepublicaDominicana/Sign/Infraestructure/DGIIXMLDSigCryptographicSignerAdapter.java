package org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Infraestructure;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.Port.Out.CryptographicSignerPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Domain.KeyAndCertificate;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.SignerDgiiUtil;
import org.springframework.stereotype.Component;

@Component
public class DGIIXMLDSigCryptographicSignerAdapter implements CryptographicSignerPort {

    @Override
    public String signDocument(String documentContent, KeyAndCertificate keyAndCertificate) {
        try {

            var inputStream = SignerDgiiUtil.stringToInputStream(documentContent);
            var signContext = SignerDgiiUtil.sign(inputStream, keyAndCertificate.privateKey(), keyAndCertificate.certificate());

            return SignerDgiiUtil.convertirStringAXML(signContext);

        } catch (Exception e) {
            throw new InfrastructureException("Failed to sign XML document", e);
        }
    }

}

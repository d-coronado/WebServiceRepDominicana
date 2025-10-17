package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out.ComprobanteToXmlPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Component
@RequiredArgsConstructor
public class ComprobanteXmlAdapter implements ComprobanteToXmlPort {

    private final ComprobanteXmlMapper comprobanteXmlMapper;

    @Override
    public String toXmlExtendido(Comprobante comprobante) {
        return generarXml(comprobanteXmlMapper.toXMLExtendido(comprobante));
    }

    @Override
    public String toXmlResumido(Comprobante comprobante) {
        return generarXml(comprobanteXmlMapper.toXMLResumido(comprobante));
    }

    private String generarXml(Object xmlModel) {
        try {
            JAXBContext context = JAXBContext.newInstance(xmlModel.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(xmlModel, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new InfrastructureException("Error generando XML del comprobante", e);
        }
    }


}

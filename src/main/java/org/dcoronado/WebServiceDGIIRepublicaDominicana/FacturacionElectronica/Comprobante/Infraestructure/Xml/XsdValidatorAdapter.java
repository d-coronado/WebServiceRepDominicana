package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out.XsdValidatorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InfrastructureException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoComprobanteTributarioEnum;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Map;

@Slf4j
@Component
public class XsdValidatorAdapter implements XsdValidatorPort {

    private static final Map<TipoComprobanteTributarioEnum, String> XSD_PATHS = Map.ofEntries(
            Map.entry(TipoComprobanteTributarioEnum.FACTURA_CREDITO_FISCAL, "xsd/e-CF-31-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.FACTURA_CONSUMO, "xsd/e-CF-32-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.NOTA_DEBITO, "xsd/e-CF-33-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.NOTA_CREDITO, "xsd/e-CF-34-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.COMPRAS, "xsd/e-CF-41-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.GASTOS_MENORES, "xsd/e-CF-43-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.REGIMENES_ESPECIALES, "xsd/e-CF-44-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.GUBERNAMENTAL, "xsd/e-CF-45-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.COMPROBANTE_EXPORTACION, "xsd/e-CF-46-v.1.0.xsd"),
            Map.entry(TipoComprobanteTributarioEnum.COMPROBANTE_PAGO_EXTERIOR, "xsd/e-CF-47-v.1.0.xsd")
    );

    private static final Map<TipoComprobanteTributarioEnum, String> XSD_PATHS_RESUMEN = Map.ofEntries(
            Map.entry(TipoComprobanteTributarioEnum.FACTURA_CONSUMO, "xsd/RFCE-32-v.1.0.xsd")
    );

    @Override
    public void execute(String xml, TipoComprobanteTributarioEnum tipoComprobante, boolean esResumen) throws Exception {
        String xsdPath = esResumen
                ? XSD_PATHS_RESUMEN.get(tipoComprobante)
                : XSD_PATHS.get(tipoComprobante);

        if (xsdPath == null) {
            throw new InfrastructureException("No se encontró archivo XSD para el tipo: "
                    + tipoComprobante + (esResumen ? " (resumen)" : ""));
        }

        log.info("Validando XML con XSD [{}]", xsdPath);

        InputStream xsdStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(xsdPath);

        if (xsdStream == null)
            throw new InfrastructureException("No se encontró el archivo XSD en resources: " + xsdPath);

        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(xsdStream));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xml)));
        } catch (SAXException e) {
            throw new ValidationException("El XML no cumple con el XSD: " + e.getMessage(), e);
        }
    }
}

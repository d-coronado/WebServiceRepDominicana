package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.resumen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CompradorResumenXml {
    @XmlElement(name = "RNCComprador")
    private String rnc;

    @XmlElement(name = "IdentificadorExtranjero")
    private String identificadorExtranjero;

    @XmlElement(name = "RazonSocialComprador")
    private String razonSocial;
}

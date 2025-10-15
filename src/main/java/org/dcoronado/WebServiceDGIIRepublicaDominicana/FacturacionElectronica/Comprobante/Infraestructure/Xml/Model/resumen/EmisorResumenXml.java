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
public class EmisorResumenXml {
    @XmlElement(name = "RNCEmisor")
    private String rnc;

    @XmlElement(name = "RazonSocialEmisor")
    private String razonSocial;

    @XmlElement(name = "FechaEmision")
    private String fechaEmision;

}
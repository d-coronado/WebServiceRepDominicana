package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class InformacionReferenciaXml {
    @XmlElement(name = "NCFModificado")
    private String NCFModificado;

    @XmlElement(name = "RNCOtroContribuyente")
    private String RNCOtroContribuyente;

    @XmlElement(name = "FechaNCFModificado")
    private String fechaNCFModificado;

    @XmlElement(name = "CodigoModificacion")
    private Integer codigoModificacion;

    @XmlElement(name = "RazonModificacion")
    private String razonModificado;

}

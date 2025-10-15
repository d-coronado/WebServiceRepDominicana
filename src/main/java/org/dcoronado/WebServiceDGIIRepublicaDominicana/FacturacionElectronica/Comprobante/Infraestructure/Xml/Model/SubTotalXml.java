package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class SubTotalXml {
    @XmlElement(name = "NumeroSubTotal")
    private Integer numeroSubTotal;

    @XmlElement(name = "DescripcionSubtotal")
    private String descripcionSubtotal;

    @XmlElement(name = "Orden")
    private Integer orden;

    @XmlElement(name = "SubTotalMontoGravadoTotal")
    private BigDecimal subTotalMontoGravadoTotal;

    @XmlElement(name = "SubTotalMontoGravadoI1")
    private BigDecimal subTotalMontoGravadoI1;

    @XmlElement(name = "SubTotalMontoGravadoI2")
    private BigDecimal subTotalMontoGravadoI2;

    @XmlElement(name = "SubTotalMontoGravadoI3")
    private BigDecimal subTotalMontoGravadoI3;

    @XmlElement(name = "SubTotaITBIS")
    private BigDecimal subTotaITBIS;

    @XmlElement(name = "SubTotaITBIS1")
    private BigDecimal subTotaITBIS1;

    @XmlElement(name = "SubTotaITBIS2")
    private BigDecimal subTotaITBIS2;

    @XmlElement(name = "SubTotaITBIS3")
    private BigDecimal subTotaITBIS3;

    @XmlElement(name = "SubTotalImpuestoAdicional")
    private BigDecimal subTotalImpuestoAdicional;

    @XmlElement(name = "SubTotalExento")
    private BigDecimal subTotalExento;

    @XmlElement(name = "MontoSubTotal")
    private BigDecimal montoSubTotal;

    @XmlElement(name = "Lineas")
    private Integer lineas;

}

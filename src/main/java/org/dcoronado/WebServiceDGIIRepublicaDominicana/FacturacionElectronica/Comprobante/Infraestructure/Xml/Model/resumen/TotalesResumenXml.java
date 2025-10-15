package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.resumen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.ResumenImpuestoAdicionalXml;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalesResumenXml {
    @XmlElement(name = "MontoGravadoTotal")
    private BigDecimal montoGravadoTotal;

    @XmlElement(name = "MontoGravadoI1")
    private BigDecimal montoGravadoI1;

    @XmlElement(name = "MontoGravadoI2")
    private BigDecimal montoGravadoI2;

    @XmlElement(name = "MontoGravadoI3")
    private BigDecimal montoGravadoI3;

    @XmlElement(name = "MontoExento")
    private BigDecimal montoExento;

    @XmlElement(name = "TotalITBIS")
    private BigDecimal totalITBIS;

    @XmlElement(name = "TotalITBIS1")
    private BigDecimal totalITBIS1;

    @XmlElement(name = "TotalITBIS2")
    private BigDecimal totalITBIS2;

    @XmlElement(name = "TotalITBIS3")
    private BigDecimal totalITBIS3;

    @XmlElement(name = "MontoImpuestoAdicional")
    private BigDecimal montoImpuestoAdicional;

    @XmlElementWrapper(name = "ImpuestosAdicionales", required = false)
    @XmlElement(name = "ImpuestoAdicional", type = ResumenImpuestoAdicionalXml.class)
    private List<ResumenImpuestoAdicionalXml> impuestosAdicionales;

    @XmlElement(name = "MontoTotal")
    private BigDecimal montoTotal;

    @XmlElement(name = "MontoNoFacturable")
    private BigDecimal montoNoFacturable;

    @XmlElement(name = "MontoPeriodo")
    private BigDecimal montoPeriodo;

}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalesXml {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoGravadoTotal", required = false)
    private BigDecimal montoGravadoTotal;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoGravadoI1", required = false)
    private BigDecimal montoGravadoI1;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoGravadoI2", required = false)
    private BigDecimal montoGravadoI2;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoGravadoI3", required = false)
    private BigDecimal montoGravadoI3;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoExento", required = false)
    private BigDecimal montoExento;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "ITBIS1", required = false)
    private String itbs1;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "ITBIS2", required = false)
    private String itbs2;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "ITBIS3", required = false)
    private String itbs3;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "TotalITBIS", required = false)
    private BigDecimal totalITBIS;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "TotalITBIS1", required = false)
    private BigDecimal totalITBIS1;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "TotalITBIS2", required = false)
    private BigDecimal totalITBIS2;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "TotalITBIS3", required = false)
    private BigDecimal totalITBIS3;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoImpuestoAdicional", required = false)
    private BigDecimal montoImpuestoAdicional;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElementWrapper(name = "ImpuestosAdicionales", required = false)
    @XmlElement(name = "ImpuestoAdicional", type = ImpuestoAdicionalXml.class)
    private List<ImpuestoAdicionalXml> impuestosAdicionales;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoTotal", required = false)
    private BigDecimal montoTotal;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoNoFacturable", required = false)
    private BigDecimal montoNoFacturable;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoPeriodo", required = false)
    private BigDecimal montoPeriodo;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "SaldoAnterior", required = false)
    private BigDecimal saldoAnterior;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "MontoAvancePago", required = false)
    private BigDecimal montoAvancePago;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "ValorPagar", required = false)
    private BigDecimal valorPagar;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "TotalITBISRetenido", required = false)
    private BigDecimal totalITBISRetenido;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "TotalISRRetencion", required = false)
    private BigDecimal totalISRRetencion;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "TotalITBISPercepcion", required = false)
    private BigDecimal totalITBISPercepcion;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @XmlElement(name = "TotalISRPercepcion", required = false)
    private BigDecimal totalISRPercepcion;
}

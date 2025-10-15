package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class OtraMonedaXml {
    @XmlElement(name = "TipoMoneda")
    private String tipoMoneda;

    @XmlElement(name = "TipoCambio")
    private BigDecimal tipoCambio;

    @XmlElement(name = "MontoGravadoTotalOtraMoneda")
    private BigDecimal montoGravadoTotalOtraMoneda;

    @XmlElement(name = "MontoGravado1OtraMoneda")
    private BigDecimal montoGravado1OtraMoneda;

    @XmlElement(name = "MontoGravado2OtraMoneda")
    private BigDecimal montoGravado2OtraMoneda;

    @XmlElement(name = "MontoGravado3OtraMoneda")
    private BigDecimal montoGravado3OtraMoneda;

    @XmlElement(name = "MontoExentoOtraMoneda")
    private BigDecimal montoExentoOtraMoneda;

    @XmlElement(name = "TotalITBISOtraMoneda")
    private BigDecimal totalITBISOtraMoneda;

    @XmlElement(name = "TotalITBIS1OtraMoneda")
    private BigDecimal totalITBIS1OtraMoneda;

    @XmlElement(name = "TotalITBIS2OtraMoneda")
    private BigDecimal totalITBIS2OtraMoneda;

    @XmlElement(name = "TotalITBIS3OtraMoneda")
    private BigDecimal totalITBIS3OtraMoneda;

    @XmlElement(name = "MontoImpuestoAdicionalOtraMoneda")
    private BigDecimal montoImpuestoAdicionalOtraMoneda;

    @XmlElement(name = "ImpuestosAdicionalesOtraMoneda")
    private List<ImpuestoAdicionalOtraMonedaXml> listImpuestoAdicionalOtraMoneda;

    @XmlElement(name = "MontoTotalOtraMoneda")
    private BigDecimal montoTotalOtraMoneda;
}


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
public class ImpuestoAdicionalOtraMonedaXml {

    @XmlElement(name = "TipoImpuestoOtraMoneda")
    private String tipoImpuestoOtraMoneda;

    @XmlElement(name = "TasaImpuestoAdicionalOtraMoneda")
    private BigDecimal tasaImpuestoAdicionalOtraMoneda;

    @XmlElement(name = "MontoImpuestoSelectivoConsumoEspecificoOtraMoneda")
    private BigDecimal montoImpuestoSelectivoConsumoEspecificoOtraMoneda;

    @XmlElement(name = "MontoImpuestoSelectivoConsumoAdvaloremOtraMoneda")
    private BigDecimal montoImpuestoSelectivoConsumoAdvaloremOtraMoneda;

    @XmlElement(name = "OtrosImpuestosAdicionalesOtraMoneda")
    private BigDecimal otrosImpuestosAdicionalesOtraMoneda;

}

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
public class DescuentoORecargoXml {
    @XmlElement(name = "NumeroLinea")
    private Integer numeroLinea;

    @XmlElement(name = "TipoAjuste")
    private String tipoAjuste;

    @XmlElement(name = "IndicadorNorma1007")
    private Integer indicadorNorma1007;

    @XmlElement(name = "DescripcionDescuentooRecargo")
    private String descripcionDescuentoORecargo;

    @XmlElement(name = "TipoValor")
    private String tipoValor;

    @XmlElement(name = "ValorDescuentooRecargo")
    private BigDecimal valorDescuentoORecargo;

    @XmlElement(name = "MontoDescuentooRecargo")
    private BigDecimal montoDescuentoORecargo;

    @XmlElement(name="MontoDescuentooRecargoOtraMoneda")
    private BigDecimal montoDescuentooRecargoOtraMoneda;

    @XmlElement(name = "IndicadorFacturacionDescuentooRecargo")
    private Integer indicadorFacturacionDescuentoORecargo;
}

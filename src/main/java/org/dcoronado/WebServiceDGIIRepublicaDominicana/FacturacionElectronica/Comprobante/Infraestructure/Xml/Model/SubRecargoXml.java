package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class SubRecargoXml {
    @XmlElement(name = "TipoSubRecargo")
    private String tipoSubRecargo;

    @XmlElement(name = "SubRecargoPorcentaje")
    private BigDecimal subRecargoPorcentaje;

    @XmlElement(name = "MontoSubRecargo")
    private BigDecimal montoSubRecargo;
}

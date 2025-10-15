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
public class SubDescuentoXml {
    @XmlElement(name = "TipoSubDescuento")
    private String tipoSubDescuento;

    @XmlElement(name = "SubDescuentoPorcentaje")
    private BigDecimal subDescuentoPorcentaje;

    @XmlElement(name = "MontoSubDescuento")
    private BigDecimal montoSubDescuento;
}

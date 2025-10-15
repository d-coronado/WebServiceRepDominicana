package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.resumen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement(name = "RFCE")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResumenXml {
    @XmlElement(name = "Encabezado")
    protected EncabezadoResumenXml encabezado;
}

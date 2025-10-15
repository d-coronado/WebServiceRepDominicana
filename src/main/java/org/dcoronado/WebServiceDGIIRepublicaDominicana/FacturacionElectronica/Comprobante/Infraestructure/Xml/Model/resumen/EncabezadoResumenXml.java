package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model.resumen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class EncabezadoResumenXml {
    @XmlElement(name = "Version")
    @NotNull(message = "")
    protected String version;

    @XmlElement(name = "IdDoc")
    protected DocResumenXml idDoc;

    @XmlElement(name = "Emisor")
    protected EmisorResumenXml emisor;

    @XmlElement(name="Comprador")
    protected CompradorResumenXml comprador;

    @XmlElement(name = "Totales")
    protected TotalesResumenXml totales;

    @XmlElement(name = "CodigoSeguridadeCF")
    protected String codigoSeguridad;

}

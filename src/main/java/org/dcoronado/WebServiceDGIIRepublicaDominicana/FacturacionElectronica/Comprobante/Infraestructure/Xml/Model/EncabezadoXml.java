package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class EncabezadoXml {
    @XmlElement(name = "Version")
    @NotNull(message = "")
    protected String version;

    @XmlElement(name = "IdDoc")
    protected DocXml idDoc;

    @XmlElement(name = "Emisor")
    protected EmisorXml emisor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name="Comprador")
    protected CompradorXml comprador;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name = "InformacionesAdicionales")
    protected InformacionXml informacionAdicional;

    @XmlElement(name = "Transporte")
    protected TransporteXml transporte;

    @XmlElement(name = "Totales")
    protected TotalesXml totales;

    @XmlElement(name = "OtraMoneda")
    protected OtraMonedaXml otraMoneda;

}

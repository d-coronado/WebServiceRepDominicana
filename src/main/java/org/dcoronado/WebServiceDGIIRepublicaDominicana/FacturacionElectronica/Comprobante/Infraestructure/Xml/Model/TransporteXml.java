package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class TransporteXml {

    @XmlElement(name = "Conductor")
    private String conductor;

    @XmlElement(name = "DocumentoTransporte")
    private String documentoTransporte;

    @XmlElement(name = "Ficha")
    private String ficha;

    @XmlElement(name = "Placa")
    private String placa;

    @XmlElement(name = "RutaTransporte")
    private String rutaTransporte;

    @XmlElement(name = "ZonaTransporte")
    private String zonaTransporte;

    @XmlElement(name = "NumeroAlbaran")
    private String numeroAlbaran;


}

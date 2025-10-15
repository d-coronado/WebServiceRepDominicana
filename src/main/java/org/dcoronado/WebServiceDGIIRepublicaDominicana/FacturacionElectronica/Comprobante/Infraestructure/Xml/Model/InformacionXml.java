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
public class InformacionXml {
    @XmlElement(name = "FechaEmbarque")
    private String fechaEmbarque;

    @XmlElement(name = "NumeroEmbarque")
    private String numeroEmbarque;

    @XmlElement(name = "NumeroContenedor")
    private String numeroContenedor;

    @XmlElement(name = "NumeroReferencia")
    private String numeroReferencia;

    @XmlElement(name = "PesoBruto")
    private double pesoBruto;

    @XmlElement(name = "PesoNeto")
    private double pesoNeto;

    @XmlElement(name = "UnidadPesoBruto")
    private Integer unidadPesoBruto;

    @XmlElement(name = "UnidadPesoNeto")
    private Integer unidadPesoNeto;

    @XmlElement(name = "CantidadBulto")
    private double cantidadBulto;

    @XmlElement(name = "UnidadBulto")
    private Integer unidadBulto;

    @XmlElement(name = "VolumenBulto")
    private double  volumenBulto;

    @XmlElement(name = "UnidadVolumen")
    private Integer unidadVolumen;
}

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
public class CompradorXml {
    @XmlElement(name = "RNCComprador")
    private String rnc;

    @XmlElement(name = "IdentificadorExtranjero")
    private String identificadorExtranjero;

    @XmlElement(name = "RazonSocialComprador")
    private String razonSocial;

    @XmlElement(name = "ContactoComprador")
    private String contacto;

    @XmlElement(name = "CorreoComprador")
    private String correo;

    @XmlElement(name="DireccionComprador")
    private String direccion;

    @XmlElement(name = "MunicipioComprador")
    private String municipio;

    @XmlElement(name = "ProvinciaComprador")
    private String provincia;

    @XmlElement(name = "FechaEntrega")
    private String fechaEntrega;

    @XmlElement(name = "ContactoEntrega")
    private String contactoEntrega;

    @XmlElement(name = "DireccionEntrega")
    private String direccionEntrega;

    @XmlElement(name = "TelefonoAdicional")
    private String telefonoAdicionl;

    @XmlElement(name = "FechaOrdenCompra")
    private String fechaOrdenCompra;

    @XmlElement(name = "NumeroOrdenCompra")
    private String numeroOrdenCompra;

    @XmlElement(name = "CodigoInternoComprador")
    private String codigoInternoComprador;

    @XmlElement(name = "ResponsablePago")
    private String responsablePago;

    @XmlElement(name = "InformacionAdicionalComprador")
    private String informacionAdicionalComprador;

}

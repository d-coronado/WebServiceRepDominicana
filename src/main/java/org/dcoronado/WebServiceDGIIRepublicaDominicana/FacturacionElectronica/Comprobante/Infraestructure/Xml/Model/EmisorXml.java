package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Xml.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class EmisorXml {
    @XmlElement(name = "RNCEmisor")
    private String rnc;

    @XmlElement(name = "RazonSocialEmisor")
    private String razonSocial;

    @XmlElement(name = "NombreComercial")
    private String nombreComercial;

    @XmlElement(name = "Sucursal")
    private String sucursal;

    @XmlElement(name = "DireccionEmisor")
    private String direccionEmisor;

    @XmlElement(name = "Municipio")
    private String municipio;

    @XmlElement(name = "Provincia")
    private String provincia;

    @XmlElementWrapper(name = "TablaTelefonoEmisor")
    @XmlElement(name = "TelefonoEmisor")
    private List<TelefonoEmisorXml> tablaListaTelefonos;

    @XmlElement(name = "CorreoEmisor")
    private String correoEmisor;

    @XmlElement(name = "WebSite")
    private String sitioWeb;

    @XmlElement(name = "ActividadEconomica")
    private String actividadEconomica;

    @XmlElement(name = "CodigoVendedor")
    private String codigoVendedor;

    @XmlElement(name = "NumeroFacturaInterna")
    private String numeroFacturaInterna;

    @XmlElement(name = "NumeroPedidoInterno")
    private String numeroPedidoInterno;

    @XmlElement(name = "ZonaVenta")
    private String zonaVenta;

    @XmlElement(name = "RutaVenta")
    private String rutaVenta;

    @XmlElement(name = "InformacionAdicionalEmisor")
    private String informacionAdicionalEmisor;

    @XmlElement(name = "FechaEmision")
    private String fechaEmision;

}
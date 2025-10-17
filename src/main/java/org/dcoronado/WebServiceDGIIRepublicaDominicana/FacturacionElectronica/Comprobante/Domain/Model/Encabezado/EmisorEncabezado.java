package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public final class EmisorEncabezado {
    private final String rnc;
    private final String razonSocial;
    private final String nombreComercial;
    private final String sucursal;
    private final String direccionEmisor;
    private final String municipio;
    private final String provincia;
    private final String fechaEmision;
    List<TelefonoEmisor> tablaListaTelefonos;
    private final String correoEmisor;
    private final String sitioWeb;
    private final String actividadEconomica;
    private final String codigoVendedor;
    private final String numeroFacturaInterna;
    private final String numeroPedidoInterno;
    private final String zonaVenta;
    private final String rutaVenta;
    private final String informacionAdicionalEmisor;

    private static final class TelefonoEmisor {
        String telefonoEmisor;
    }
}

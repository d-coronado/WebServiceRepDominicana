package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Encabezado;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Utils.FechaUtil;

import java.util.List;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

public record EmisorEncabezado(
        String rnc,
        String razonSocial,
        String nombreComercial,
        String sucursal,
        String direccionEmisor,
        String municipio,
        String provincia,
        List<TelefonoEmisor> tablaListaTelefonos,
        String correoEmisor,
        String sitioWeb,
        String actividadEconomica,
        String codigoVendedor,
        String numeroFacturaInterna,
        String numeroPedidoInterno,
        String zonaVenta,
        String rutaVenta,
        String informacionAdicionalEmisor,
        String fechaEmision
) {

    public EmisorEncabezado {
        notBlank(rnc, "rnc del emisor required");
        notBlank(razonSocial, "razon social del emisor required");
        notBlank(direccionEmisor, "direccion emisor required");
        notBlank(fechaEmision, "fecha Emision required");
        if (!FechaUtil.tieneFormatoFechaValido(fechaEmision))
            throw new InvalidArgumentException("fechaEmision tiene formato inválido.Debe tener formato dd-MM-AAAA");
    }

    public record TelefonoEmisor(
            String telefonoEmisor
    ) {
    }

}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class CompradorEncabezado{
    private final String rnc;
    private final String identificadorExtranjero;
    private final String razonSocial;
    private final String contacto;
    private final String correo;
    private final String direccion;
    private final String municipio;
    private final String provincia;
    private final String fechaEntrega;
    private final String contactoEntrega;
    private final String direccionEntrega;
    private final String telefonoAdicionl;
    private final String fechaOrdenCompra;
    private final String numeroOrdenCompra;
    private final String codigoInternoComprador;
    private final String responsablePago;
    private final String informacionAdicionalComprador;
}

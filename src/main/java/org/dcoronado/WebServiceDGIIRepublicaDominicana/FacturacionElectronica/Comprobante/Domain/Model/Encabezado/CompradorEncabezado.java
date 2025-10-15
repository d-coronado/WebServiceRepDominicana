package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado;

import lombok.Builder;

@Builder
public record CompradorEncabezado(
        String rnc,
        String identificadorExtranjero,
        String razonSocial,
        String contacto,
        String correo,
        String direccion,
        String municipio,
        String provincia,
        String fechaEntrega,
        String contactoEntrega,
        String direccionEntrega,
        String telefonoAdicionl,
        String fechaOrdenCompra,
        String numeroOrdenCompra,
        String codigoInternoComprador,
        String responsablePago,
        String informacionAdicionalComprador
) {
}

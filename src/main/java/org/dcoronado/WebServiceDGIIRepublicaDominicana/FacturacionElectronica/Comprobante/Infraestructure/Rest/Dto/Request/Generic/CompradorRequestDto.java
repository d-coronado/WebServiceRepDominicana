package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.Generic;

import jakarta.validation.constraints.Size;

public record CompradorRequestDto(

        @Size(max = 11, message = "{}")
        String rnc,

        @Size(max = 20, message = "{}")
        String identificadorExtranjero,

        @Size(max = 150, message = "{}")
        String razonSocial,

        String contactoNombre,
        String contactoTelefono,
        String correoElectronico,
        String direccion,
        String municipio,
        String provincia,

        String pais,
        String fechaEntrega,
        String nombreContactoEntrega,
        String direccionEntrega,
        String telefonoContactoEntrega,

        String fechaOrdenCompra,

        @Size(max = 20, message = "{}")
        String numeroOrdenCompra,

        String codigoInternoComprador,
        String responsablePago,
        String informacionAdicionalComprador
) {}

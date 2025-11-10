package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;

public record UpdateLicenciaCommand(
        Long licenciaId,
        String rnc,
        String razonSocial,
        String direccionFiscal,
        String alias,
        String nombreContacto,
        String telefono,
        AmbienteEnum ambiente
) {

    public UpdateLicenciaCommand{
        required(licenciaId,"Licencia ID required");
        notBlank(rnc,"RNC required");
        notBlank(direccionFiscal,"Direccion Fiscal required");
        required(ambiente,"Ambiente required");
    }
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;

public record CreateLicenciaCommand(
        String rnc,
        String razonSocial,
        String direccionFiscal,
        String alias,
        String nombreContacto,
        String telefono,
        AmbienteEnum ambiente
) {
    public CreateLicenciaCommand {
        notBlank(rnc,"RNC required");
        notBlank(direccionFiscal,"Direccion Fiscal required");
        required(ambiente,"Ambiente required");
    }
}
package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.CreateLicenciaCommand;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;

public interface CreateLicenciaUseCase {
    Licencia createLicencia(CreateLicenciaCommand command);
}

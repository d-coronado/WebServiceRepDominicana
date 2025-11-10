package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.UpdateLicenciaCommand;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;

public interface UpdateLicenciaUseCase {
    Licencia updateLicencia(UpdateLicenciaCommand command);
}

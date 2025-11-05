package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;

public interface SetupDatabaseLicenciaUseCase {
    void execute(Licencia licencia);
}

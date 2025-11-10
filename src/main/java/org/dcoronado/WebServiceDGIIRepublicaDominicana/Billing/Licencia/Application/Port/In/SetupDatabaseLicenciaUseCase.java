package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Command.SetupBDCommand;

public interface SetupDatabaseLicenciaUseCase {
    void execute(SetupBDCommand command);
}

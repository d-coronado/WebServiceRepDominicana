package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DatabaseSetupData;

public interface DatabaseManagerPort {
    String execute(DatabaseSetupData databaseSetupData);
}

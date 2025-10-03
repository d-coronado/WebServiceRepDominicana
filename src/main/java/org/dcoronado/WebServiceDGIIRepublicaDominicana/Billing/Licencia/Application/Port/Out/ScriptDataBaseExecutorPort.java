package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DbConnectionInfo;

public interface ScriptDataBaseExecutorPort {
    void executeScript(DbConnectionInfo data);
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.ScriptExecutionData;

public interface ScriptDataBaseExecutorPort {
    void executeScript(ScriptExecutionData data);
}

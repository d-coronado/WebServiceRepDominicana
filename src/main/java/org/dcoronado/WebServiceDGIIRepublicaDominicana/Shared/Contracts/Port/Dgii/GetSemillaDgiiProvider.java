package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.Dgii;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

public interface GetSemillaDgiiProvider {
    String execute(AmbienteEnum ambiente);
}

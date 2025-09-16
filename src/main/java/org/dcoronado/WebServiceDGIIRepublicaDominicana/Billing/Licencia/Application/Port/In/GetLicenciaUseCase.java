package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;

public interface GetLicenciaUseCase {
    Licencia findById(Long id);
    Licencia finByRnc(String rnc);
}

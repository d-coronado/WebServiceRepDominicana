package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;

import java.util.Optional;

public interface GetLicenciaUseCase {
    Optional<Licencia> findById(Long id);
    Optional<Licencia> finByRnc(String rnc);
}

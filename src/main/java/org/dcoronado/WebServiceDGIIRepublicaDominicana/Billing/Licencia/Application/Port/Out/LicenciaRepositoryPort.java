package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;

import java.util.Optional;

public interface LicenciaRepositoryPort {
    Licencia save(Licencia licencia);

    Optional<Licencia> findById(Long id);

    Optional<Licencia> findByRnc(String rnc);
}

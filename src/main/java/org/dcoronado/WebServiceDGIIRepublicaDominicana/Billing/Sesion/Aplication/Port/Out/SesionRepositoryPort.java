package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;

import java.util.Optional;

public interface SesionRepositoryPort {
    Sesion save(Sesion sesion);
    Optional<Sesion> findSesionActiveByRnc(Sesion sesion);
}

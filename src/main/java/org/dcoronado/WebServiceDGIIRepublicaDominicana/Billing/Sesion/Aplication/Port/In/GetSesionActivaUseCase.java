package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;

import java.util.Optional;

public interface GetSesionActivaUseCase {
    Optional<Sesion> getSesionActiva(Sesion sesion);
}

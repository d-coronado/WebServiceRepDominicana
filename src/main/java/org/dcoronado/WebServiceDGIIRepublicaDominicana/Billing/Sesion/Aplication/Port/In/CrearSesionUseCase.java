package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;

public interface CrearSesionUseCase {
    Sesion crearSesion(Sesion sesion) throws Exception;
}

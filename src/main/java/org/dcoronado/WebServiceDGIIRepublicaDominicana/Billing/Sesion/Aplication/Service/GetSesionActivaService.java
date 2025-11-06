package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.GetSesionActivaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.SesionRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;


/**
 * Servicio de aplicación encargado de obtener la sesión activa de la dgii de un RNC.
 * Verifica que la sesión esté vigente en el momento de la consulta.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetSesionActivaService implements GetSesionActivaUseCase {

    private final SesionRepositoryPort sesionRepositoryPort;

    /**
     * Obtiene la sesión activa para el RNC indicado en el objeto Sesion.
     * Valida los parámetros de entrada y filtra por sesiones vigentes según UTC.
     *
     * @param sesion objeto de dominio con el RNC y parámetros de la sesión a consultar
     * @return un Optional con la sesión activa si existe, vacío en caso contrario
     */
    @Override
    public Optional<Sesion> getSesionActiva(Sesion sesion) {
        log.info(" INICIO - Proceso de consulta de sesión activa para RNC: {} | Ambiente: {}", sesion.getRnc(), sesion.getAmbiente());

        log.info("[1] Validando parámetros genéricos de la sesión.");
        // Validar parámetros de la sesión
        sesion.validarParametrosGenericos();

        // Obtener fecha y hora actual en UTC
        LocalDateTime ahoraUtc = LocalDateTime.now(ZoneOffset.UTC);

        // Consultar sesión activa en el repositorio
        log.info("[2] Buscando sesión activa en repositorio.");
        Optional<Sesion> sesionActiva =  sesionRepositoryPort.findSesionActiveByRnc(sesion, ahoraUtc);

        if (sesionActiva.isPresent()) {
            Sesion encontrada = sesionActiva.get();
            log.info("[3] Sesión activa encontrada: ID={}", encontrada.getId());
        } else {
            log.info("[3] No se encontró sesión activa para RNC: {}", sesion.getRnc());
        }

        return sesionActiva;
    }

}

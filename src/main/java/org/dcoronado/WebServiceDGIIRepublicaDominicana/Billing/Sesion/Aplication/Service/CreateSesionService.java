package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.Dgii.GetSemillaDgiiProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.LicenciaProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.SignProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.Dgii.ValidarSemillaDgiiProvider;
import org.springframework.stereotype.Service;


/**
 * Servicio de aplicación encargado de crear una nueva sesión.
 * Orquesta la lógica de validación de parámetros, obtención de licencia,
 * generación y firma de semilla, validación con DGII y persistencia de la sesión.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateSesionService implements CrearSesionUseCase {

    private final SignProvider signProvider;
    private final LicenciaProvider licenciaProvider;
    private final GetSemillaDgiiProvider getSemillaDgiiProvider;
    private final ValidarSemillaDgiiProvider validarSemillaDgiiProvider;
    private final SesionRepositoryPort sesionRepositoryPort;


    /**
     * Crea una nueva sesión para el ambienteEnum indicado.
     * Valida los parámetros, la licencia y genera la semilla firmada para DGII.
     *
     * @param sesion objeto de dominio que contiene los datos de la sesión a crear
     * @return la sesión creada y persistida en el repositorio
     * @throws Exception si ocurre algún error al firmar o validar la semilla
     */
    @Override
    public Sesion crearSesion(Sesion sesion) throws Exception {

        log.info("INICIO - Proceso de creación de sesión DGII para RNC: {} | Ambiente: {}", sesion.getRnc(), sesion.getAmbiente());

        log.info("[1] Validando parámetros genéricos de la sesión.");
        // Validaciones básicas de la sesión
        sesion.validarParametrosGenericos();

        // Obtener información de la licencia según RNC
        log.info("[2] Obteniendo información de licencia para RNC: {}", sesion.getRnc());
        LicenciaInfoDto licenciaInfoDto = licenciaProvider.getLicenciaInfoByRnc(sesion.getRnc());

        // Validaciones específicas de acceso según la licencia
        log.debug("[3] Validando acceso y requisitos para crear una sesion para la licencia.");
        sesion.validarAccesLimitAmbienteLicencia(licenciaInfoDto.limitAccessAmbiente());
        sesion.validarLicenciaRequireForSesion(licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());

        // Obtener semilla del proveedor y firmarla
        log.info("[4] Solicitando semilla a DGII para ambiente: {}", sesion.getAmbiente());
        String semilla = getSemillaDgiiProvider.execute(sesion.getAmbiente());

        log.info("[5] Firmando semilla con certificado de licencia.");
        String semillaFirmada = signProvider.execute(semilla, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());

        // Validar semilla firmada con DGII
        log.info("[6] Validando semilla firmada con DGII.");
        InfoTokenDgiiDto result = validarSemillaDgiiProvider.execute(sesion.getAmbiente(), semillaFirmada);

        // Asignar datos de la sesión y persistir
        log.info("[7] Persistiendo sesión en repositorio...");
        sesion.setDatosSesion(result.token(), result.fechaExpedido(), result.fechaExpira());
        return sesionRepositoryPort.save(sesion);
    }
}

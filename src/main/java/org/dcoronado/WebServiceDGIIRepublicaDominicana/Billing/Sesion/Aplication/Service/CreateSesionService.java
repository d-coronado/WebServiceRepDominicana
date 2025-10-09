package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.GetSemillaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.LicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SignProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.ValidarSemillaProviderPort;
import org.springframework.stereotype.Service;


/**
 * Servicio de aplicación encargado de crear una nueva sesión.
 * Orquesta la lógica de validación de parámetros, obtención de licencia,
 * generación y firma de semilla, validación con DGII y persistencia de la sesión.
 */
@Service
@RequiredArgsConstructor
public class CreateSesionService implements CrearSesionUseCase {

    private final SignProviderPort signProviderPort;
    private final LicenciaProviderPort licenciaProviderPort;
    private final GetSemillaProviderPort getSemillaProviderPort;
    private final ValidarSemillaProviderPort validarSemillaProviderPort;
    private final SesionRepositoryPort sesionRepositoryPort;


    /**
     * Crea una nueva sesión para el ambiente indicado.
     * Valida los parámetros, la licencia y genera la semilla firmada para DGII.
     *
     * @param sesion objeto de dominio que contiene los datos de la sesión a crear
     * @return la sesión creada y persistida en el repositorio
     * @throws Exception si ocurre algún error al firmar o validar la semilla
     */
    @Override
    public Sesion crearSesion(Sesion sesion) throws Exception {

        // Validaciones básicas de la sesión
        sesion.validarParametrosGenericos();

        // Obtener información de la licencia según RNC
        LicenciaInfoDto licenciaInfoDto = licenciaProviderPort.getLicenciaInfoByRnc(sesion.getRnc());

        // Validaciones específicas de acceso según la licencia
        sesion.validarAccesLimitAmbienteLicencia(licenciaInfoDto.limitAccessAmbiente());
        sesion.validarLicenciaRequireForSesion(licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());

        // Obtener semilla del proveedor y firmarla
        String semilla = getSemillaProviderPort.execute(sesion.getAmbiente());
        String semillaFirmada = signProviderPort.execute(semilla, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());

        // Validar semilla firmada con DGII
        InfoTokenDgiiDto result = validarSemillaProviderPort.execute(sesion.getAmbiente(), semillaFirmada);

        // Asignar datos de la sesión y persistir
        sesion.setDatosSesion(result.token(), result.fechaExpedido(), result.fechaExpira());
        return sesionRepositoryPort.save(sesion);
    }
}

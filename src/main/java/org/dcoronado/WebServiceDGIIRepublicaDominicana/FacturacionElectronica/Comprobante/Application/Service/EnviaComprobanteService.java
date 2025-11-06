package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.SesionInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.Dgii.EnviaComprobanteDgiiProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In.EnviaComprobanteUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out.ComprobanteToXmlPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out.XsdValidatorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoOperacionArchivoLicenciaEnum;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;

/**
 * Servicio encargado de orquestar el envío de un comprobante electrónico hacia la DGII.
 * Este servicio realiza validaciones, genera el XML extendido y resumido (según aplique),
 * firma el comprobante, valida el XSD, guarda los archivos en el directorio de la licencia,
 * gestiona la sesión con la DGII y finalmente realiza el envío.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EnviaComprobanteService implements EnviaComprobanteUseCase {

    private final LicenciaProvider licenciaProvider;
    private final SesionProvider sesionProvider;
    private final SignProvider signProvider;
    private final SaveFileLicenciaProvider saveFileLicenciaProvider;
    private final EnviaComprobanteDgiiProvider enviaComprobanteDgiiProvider;
    private final ComprobanteToXmlPort comprobanteToXmlPort;
    private final XsdValidatorPort xsdValidatorPort;


    /**
     * Ejecuta el proceso completo de validación, firma, almacenamiento y envío del comprobante a la DGII.
     *
     * @param comprobante objeto con toda la información tributaria del documento a enviar.
     * @return el comprobante con los datos actualizados luego del envío.
     * @throws Exception si ocurre algún error en la firma, validación o envío del comprobante.
     */
    @Override
    public Comprobante execute(Comprobante comprobante) throws Exception {

        log.info("INICIO - Proceso de envío de comprobante - EMISOR con RNC: {}", comprobante.getEncabezado().getEmisorEncabezado().getRnc());

        // Validaciones generales del comprobante
        log.info("[1] Validando estructura general del comprobante.");
        comprobante.validar();

        // Validaciones especificas segun tipo de comprobante y enriquecer segun tipo de comprobante
        log.info("[2] Validando especificamente según tipo de comprobante : {}", comprobante.getEncabezado().getDocEncabezado().getTipoComprobanteTributarioEnum());
        final TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum = comprobante.getEncabezado().getDocEncabezado().getTipoComprobanteTributarioEnum();
        final String secuenciaComprobante = comprobante.getEncabezado().getDocEncabezado().getSecuencia();
        comprobante.validarSegunTipoComprobante(tipoComprobanteTributarioEnum);

        log.info("[3] Enriqueciendo comprobante tipo: {}", tipoComprobanteTributarioEnum);
        comprobante.enriquecer(tipoComprobanteTributarioEnum, secuenciaComprobante);

        // Obtener licencia del emisor
        String rncEmisor = comprobante.getEncabezado().getEmisorEncabezado().getRnc();
        log.info("[4] Buscando Licencia con RNC: {}", rncEmisor);
        LicenciaInfoDto licenciaInfoDto = licenciaProvider.getLicenciaInfoByRnc(rncEmisor);

        // Validar coherencia entre comprobante y licencia (seguridad) ---> Despues lo hacemos

        // Variables para el XML firmado a enviar a DGII
        String xmlFirmadoParaEnvioDgii;

        // Generar XML extendido y firmarlo
        log.info("[5] Generando XML extendido.");
        final String comprobanteXmlExtendido = comprobanteToXmlPort.toXmlExtendido(comprobante);
        final String comprobanteXmlExtendidoFirmado = signProvider.execute(comprobanteXmlExtendido, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());

        // Validar XML extendido firmado con el XSD correspondiente
        log.info("[6] Validando XML extendido con XSD correspondiente.");
        xsdValidatorPort.execute(comprobanteXmlExtendidoFirmado, tipoComprobanteTributarioEnum, comprobante.isEsResumen());

        // Extraer hash de la firma del XML extendido
        log.info("[7] Extrayendo Hash de firma del XML extendido.");
        final String hashFirma = signProvider.executeHash(comprobanteXmlExtendidoFirmado);
        comprobante.asignarFirmaExtendida(hashFirma);

        xmlFirmadoParaEnvioDgii = comprobanteXmlExtendidoFirmado;

        // Si es comprobante en formato resumido, generar XML resumido y firmarlo
        log.info("[8] Verificando si el comprobante debe enviarse en formato resumido.");
        comprobante.setEsResumen(tipoComprobanteTributarioEnum);

        if (comprobante.isEsResumen()) {
            required(comprobante.getCodigoSeguridad(), "Codigo de seguridad es requerido para comprobante en formato resumido");
            log.info("[8.1] Generando XML resumido...");
            final String comprobanteXmlResumido = comprobanteToXmlPort.toXmlResumido(comprobante);
            log.info("[8.2] Firmando XML resumido.");
            final String comprobanteXmlResumidoFirmado = signProvider.execute(comprobanteXmlResumido, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());
            log.info("[8.3] Validando XML resumido firmado con XSD correspondiente.");
            xsdValidatorPort.execute(comprobanteXmlResumidoFirmado, tipoComprobanteTributarioEnum, comprobante.isEsResumen());
            xmlFirmadoParaEnvioDgii = comprobanteXmlResumidoFirmado;
        }


        // Verificar existencia del comprobante en BD (tipo,secuencia,ambiente) -> despues lo hacemos.
        boolean verificarExistenciaComprobanteEnBd = false;

        if (verificarExistenciaComprobanteEnBd) {
            throw new IllegalStateException("El comprobante ya existe en la base de datos");
        } else {

            log.info("[9] Guardando Archivo XM en el directorio de licencia.");
            final AmbienteEnum ambienteEnum = comprobante.getEncabezado().getDocEncabezado().getAmbienteEnum();

            final String nombreArchivo = licenciaInfoDto.rnc().concat(comprobante.getEncf()).concat(".xml");
            saveFileLicenciaProvider.guardarArchivoContexto(licenciaInfoDto.rnc(),
                    ContextoArchivoEnum.COMPROBANTE,
                    TipoOperacionArchivoLicenciaEnum.EMISION,
                    ambienteEnum,
                    tipoComprobanteTributarioEnum,
                    nombreArchivo,
                    comprobanteXmlExtendidoFirmado.getBytes(StandardCharsets.UTF_8));

            log.info("[10] Verificando sesión activa DGII...");
            // Si no existe obtener una sesion (token dgii)
            SesionInfoDto sesionInfoDto = sesionProvider
                    .obtenerSesionActiva(licenciaInfoDto.rnc(), ambienteEnum)
                    .orElseGet(() -> {
                        try {
                            log.info("[10.1] No existe sesión activa. Creando nueva sesión DGII...");
                            return sesionProvider.crear(licenciaInfoDto.rnc(), ambienteEnum);
                        } catch (Exception e) {
                            log.error("[10.1] Error creando sesión DGII", e);
                            throw new RuntimeException("No se pudo crear la sesión", e);
                        }
                    });

            // enviar a la dgii
            log.info("[11] Enviando comprobante a DGII.");
            if(comprobante.isEsResumen()){
                enviaComprobanteDgiiProvider.enviaComprobanteResumenProvider(ambienteEnum, sesionInfoDto.token(), xmlFirmadoParaEnvioDgii.getBytes(StandardCharsets.UTF_8));
            } else {
                enviaComprobanteDgiiProvider.enviaComprobanteProvider(ambienteEnum, sesionInfoDto.token(), xmlFirmadoParaEnvioDgii.getBytes(StandardCharsets.UTF_8));
            }

            log.info("[12] Proceso finalizado correctamente para secuencia: {}", secuenciaComprobante);
            // retornar el comprobante con la info de la respuesta de DGII
            return comprobante;
        }
    }
}

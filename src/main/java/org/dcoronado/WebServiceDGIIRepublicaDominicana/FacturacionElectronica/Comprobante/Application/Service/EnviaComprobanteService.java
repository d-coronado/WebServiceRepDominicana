package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Service;

import lombok.RequiredArgsConstructor;
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


    @Override
    public Comprobante execute(Comprobante comprobante) throws Exception {

        // Validaciones generales del comprobante
        comprobante.validar();

        // Validaciones especificas segun tipo de comprobante y enriquecer segun tipo de comprobante
        final TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum = comprobante.getEncabezado().getDocEncabezado().getTipoComprobanteTributarioEnum();
        final String secuenciaComprobante = comprobante.getEncabezado().getDocEncabezado().getSecuencia();
        comprobante.validarSegunTipoComprobante(tipoComprobanteTributarioEnum);
        comprobante.enriquecer(tipoComprobanteTributarioEnum, secuenciaComprobante);

        // Obtener licencia del emisor
        String rncEmisor = comprobante.getEncabezado().getEmisorEncabezado().getRnc();
        LicenciaInfoDto licenciaInfoDto = licenciaProvider.getLicenciaInfoByRnc(rncEmisor);

        // Validar coherencia entre comprobante y licencia (seguridad) ---> Despues lo hacemos

        // Variables para el XML firmado a enviar a DGII
        String xmlFirmadoParaEnvioDgii;

        // Generar XML extendido y firmarlo
        final String comprobanteXmlExtendido = comprobanteToXmlPort.toXmlExtendido(comprobante);
        final String comprobanteXmlExtendidoFirmado = signProvider.execute(comprobanteXmlExtendido, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());

        // Validar XML extendido firmado con el XSD correspondiente
        xsdValidatorPort.execute(comprobanteXmlExtendidoFirmado, tipoComprobanteTributarioEnum, comprobante.isEsResumen());

        // Extraer hash de la firma del XML extendido
        final String hashFirma = signProvider.executeHash(comprobanteXmlExtendidoFirmado);
        comprobante.asignarFirmaExtendida(hashFirma);

        xmlFirmadoParaEnvioDgii = comprobanteXmlExtendidoFirmado;

        // Si es comprobante en formato resumido, generar XML resumido y firmarlo
        comprobante.setEsResumen(tipoComprobanteTributarioEnum);
        if (comprobante.isEsResumen()) {
            required(comprobante.getCodigoSeguridad(), "Codigo de seguridad es requerido para comprobante en formato resumido");
            final String comprobanteXmlResumido = comprobanteToXmlPort.toXmlResumido(comprobante);
            final String comprobanteXmlResumidoFirmado = signProvider.execute(comprobanteXmlResumido, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());
            xsdValidatorPort.execute(comprobanteXmlResumidoFirmado, tipoComprobanteTributarioEnum, comprobante.isEsResumen());
            xmlFirmadoParaEnvioDgii = comprobanteXmlResumidoFirmado;
        }


        // Verificar existencia del comprobante en BD (tipo,secuencia,ambiente) -> despues lo hacemos.
        boolean verificarExistenciaComprobanteEnBd = false;

        if (verificarExistenciaComprobanteEnBd) {
            throw new IllegalStateException("El comprobante ya existe en la base de datos");
        } else {

            final AmbienteEnum ambienteEnum = comprobante.getEncabezado().getDocEncabezado().getAmbienteEnum();

            final String nombreArchivo = licenciaInfoDto.rnc().concat(comprobante.getEncf()).concat(".xml");
            saveFileLicenciaProvider.guardarArchivoContexto(licenciaInfoDto.rnc(),
                    ContextoArchivoEnum.COMPROBANTE,
                    TipoOperacionArchivoLicenciaEnum.EMISION,
                    ambienteEnum,
                    tipoComprobanteTributarioEnum,
                    nombreArchivo,
                    comprobanteXmlExtendidoFirmado.getBytes(StandardCharsets.UTF_8));


            // Si no existe obtener una sesion (token dgii)
            SesionInfoDto sesionInfoDto = sesionProvider
                    .obtenerSesionActiva(licenciaInfoDto.rnc(), ambienteEnum)
                    .orElseGet(() -> {
                        try {
                            return sesionProvider.crear(licenciaInfoDto.rnc(), ambienteEnum);
                        } catch (Exception e) {
                            throw new RuntimeException("No se pudo crear la sesión", e);
                        }
                    });

            // enviar a la dgii
            if(comprobante.isEsResumen()){
                enviaComprobanteDgiiProvider.enviaComprobanteResumenProvider(ambienteEnum, sesionInfoDto.token(), xmlFirmadoParaEnvioDgii.getBytes(StandardCharsets.UTF_8));
            } else {
                enviaComprobanteDgiiProvider.enviaComprobanteProvider(ambienteEnum, sesionInfoDto.token(), xmlFirmadoParaEnvioDgii.getBytes(StandardCharsets.UTF_8));
            }

            // retornar el comprobante con la info de la respuesta de DGII
            return comprobante;
        }
    }
}

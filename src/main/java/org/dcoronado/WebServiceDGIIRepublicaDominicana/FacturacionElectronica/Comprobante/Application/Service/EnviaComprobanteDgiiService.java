package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.SesionInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.LicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SaveFileLicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SesionProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SignProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In.EnviaComprobanteDgiiUseCase;
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
public class EnviaComprobanteDgiiService implements EnviaComprobanteDgiiUseCase {

    private final LicenciaProviderPort licenciaProviderPort;
    private final SesionProviderPort sesionProviderPort;
    private final SignProviderPort signProviderPort;
    private final ComprobanteToXmlPort comprobanteToXmlPort;
    private final XsdValidatorPort xsdValidatorPort;
    private final SaveFileLicenciaProviderPort saveFileLicenciaProviderPort;


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
        LicenciaInfoDto licenciaInfoDto = licenciaProviderPort.getLicenciaInfoByRnc(rncEmisor);

        // Validar coherencia entre comprobante y licencia (seguridad) ---> Despues lo hacemos

        // Variables para el XML firmado a enviar a DGII
        String comprobanteXmlFirmadoEnvioDgii;

        // Generar XML extendido y firmarlo
        final String comprobanteXmlExtendido = comprobanteToXmlPort.toXmlExtendido(comprobante);
        final String comprobanteXmlExtendidoFirmado = signProviderPort.execute(comprobanteXmlExtendido, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());

        // Validar XML extendido firmado con el XSD correspondiente
        xsdValidatorPort.execute(comprobanteXmlExtendidoFirmado, tipoComprobanteTributarioEnum, comprobante.isEsResumen());

        // Extraer hash de la firma del XML extendido
        final String hashFirma = signProviderPort.executeHash(comprobanteXmlExtendidoFirmado);
        comprobante.asignarFirmaExtendida(hashFirma);

        comprobanteXmlFirmadoEnvioDgii = comprobanteXmlExtendidoFirmado;

        // Si es comprobante en formato resumido, generar XML resumido y firmarlo
        comprobante.setEsResumen(tipoComprobanteTributarioEnum);
        if (comprobante.isEsResumen()) {
            required(comprobante.getCodigoSeguridad(), "Codigo de seguridad es requerido para comprobante en formato resumido");
            final String comprobanteXmlResumido = comprobanteToXmlPort.toXmlResumido(comprobante);
            final String comprobanteXmlResumidoFirmado = signProviderPort.execute(comprobanteXmlResumido, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());
            xsdValidatorPort.execute(comprobanteXmlResumidoFirmado, tipoComprobanteTributarioEnum, comprobante.isEsResumen());
            comprobanteXmlFirmadoEnvioDgii = comprobanteXmlResumidoFirmado;
        }


        // Verificar existencia del comprobante en BD (tipo,secuencia,ambiente) -> despues lo hacemos.
        boolean verificarExistenciaComprobanteEnBd = false;

        if (verificarExistenciaComprobanteEnBd) {
            throw new IllegalStateException("El comprobante ya existe en la base de datos");
        } else {

            final AmbienteEnum ambienteEnum = comprobante.getEncabezado().getDocEncabezado().getAmbienteEnum();

            final String nombreArchivo = licenciaInfoDto.rnc().concat(comprobante.getEncf()).concat(".xml");
            saveFileLicenciaProviderPort.guardarArchivoContexto(licenciaInfoDto.rnc(),
                    ContextoArchivoEnum.COMPROBANTE,
                    TipoOperacionArchivoLicenciaEnum.EMISION,
                    ambienteEnum,
                    tipoComprobanteTributarioEnum,
                    nombreArchivo,
                    comprobanteXmlExtendidoFirmado.getBytes(StandardCharsets.UTF_8));


            // Si no existe obtener una sesion (token dgii)
            SesionInfoDto sesionInfoDto = sesionProviderPort
                    .obtenerSesionActiva(licenciaInfoDto.rnc(), ambienteEnum)
                    .orElseGet(() -> {
                        try {
                            return sesionProviderPort.crear(licenciaInfoDto.rnc(), ambienteEnum);
                        } catch (Exception e) {
                            throw new RuntimeException("No se pudo crear la sesión", e);
                        }
                    });

            // enviar a la dgii

            // guardar respuesta en BD

            // retornar el comprobante con la info de la respuesta de DGII
            return comprobante;
        }
    }
}

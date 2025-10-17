package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.LicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SesionProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SignProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In.EnviaComprobanteDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out.ComprobanteToXmlPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out.XsdValidatorPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnviaComprobanteDgiiService implements EnviaComprobanteDgiiUseCase {

    private final LicenciaProviderPort licenciaProviderPort;
    private final SesionProviderPort sesionProviderPort;
    private final SignProviderPort signProviderPort;
    private final ComprobanteToXmlPort comprobanteToXmlPort;
    private final XsdValidatorPort xsdValidatorPort;


    @Override
    public Comprobante execute(Comprobante comprobante) throws Exception {

        // OBLIGATORIO, NO OLVIDARRRRRRR, este valida estructura.
        comprobante.validar();

        // Validaciones especificas segun tipo de comprobante y enriquecer segun tipo de comprobante
        final TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum = comprobante.getEncabezado().getDocEncabezado().getTipoComprobanteTributarioEnum();
        final String secuenciaComprobante = comprobante.getEncabezado().getDocEncabezado().getSecuencia();

        comprobante.validarSegunTipoComprobante(tipoComprobanteTributarioEnum);
        comprobante.enriquecer(tipoComprobanteTributarioEnum, secuenciaComprobante);

        // Para saber luego el formato de xml que se deber crear.
        comprobante.setEsResumen(tipoComprobanteTributarioEnum);

        // Obtener licencia del emisor
        String rncEmisor = comprobante.getEncabezado().getEmisorEncabezado().getRnc();
        LicenciaInfoDto licenciaInfoDto = licenciaProviderPort.getLicenciaInfoByRnc(rncEmisor);

        // Validar coherencia entre comprobante y licencia (seguridad) ---> Despues lo hacemos

        // Generar XML del comprobante
        String comprobanteXml = comprobante.isEsResumen() ? comprobanteToXmlPort.toXmlResumido(comprobante) : comprobanteToXmlPort.toXmlExtendido(comprobante);

        // Firmar XML con certificado de la licencia
        String comprobanteXmlFirmado = signProviderPort.execute(comprobanteXml, licenciaInfoDto.pathCertificado(), licenciaInfoDto.claveCertificado());

        // Validar XML Firmado con el XSD correspondiente
         xsdValidatorPort.execute(comprobanteXmlFirmado, tipoComprobanteTributarioEnum, comprobante.isEsResumen());


        // Agregar firma y código de seguridad al comprobante
        // comprobante.agregarFirmaYCodigo(firma);

        // Verificar si el comprobante ya existe en BD (evita duplicados)


        // Buscar en BD el comprobante(tipo,secuencia,ambiente)
        // Si no existe obtener una sesion (token)
        // guardar xmlfirmado en el directorio de la licencia.
        // enviar a la dgii
        // guardar respuesta
        // retornar comprobante


        // Obtener sesión activa o crear una nueva
//        SesionInfoDto sesionInfoDto = sesionProviderPort
//                .obtenerSesionActiva(licenciaInfoDto.rnc(), comprobante.getAmbienteEnum())
//                .orElseGet(() -> {
//                    try {
//                        return sesionProviderPort.crear(licenciaInfoDto.rnc(), comprobante.getAmbienteEnum());
//                    } catch (Exception e) {
//                        throw new RuntimeException("No se pudo crear la sesión", e);
//                    }
//                });

        // Enviar comprobante a la DGII

        // Guardar comprobante con respuesta

        // Retornar comprobante con resultado final

        return comprobante;


    }
}

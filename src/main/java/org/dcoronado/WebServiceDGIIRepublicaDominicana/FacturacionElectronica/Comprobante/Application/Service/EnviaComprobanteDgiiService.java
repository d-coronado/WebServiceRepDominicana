package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.SesionInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.LicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SesionProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In.EnviaComprobanteDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Out.ComprobanteToXmlPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.EmisorEncabezado;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidator;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidatorFactory;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EnviaComprobanteDgiiService implements EnviaComprobanteDgiiUseCase {

    private final LicenciaProviderPort licenciaProviderPort;
    private final SesionProviderPort sesionProviderPort;
    private final ComprobanteToXmlPort comprobanteToXmlPort;


    @Override
    public Comprobante execute(Comprobante comprobante) {

        // Obtiene una instancia de validacion segun el tipo de comprobante.
        ComprobanteValidator comprobanteValidator = ComprobanteValidatorFactory.getValidator(comprobante.tipoComprobanteTributarioEnum());

        // Valida segun la instancia obtenida.
        comprobanteValidator.execute(comprobante);

        EmisorEncabezado emisorInfo = comprobante.encabezado().emisorEncabezado();

        // Validación con licencia
        LicenciaInfoDto licenciaInfoDto = licenciaProviderPort.getLicenciaInfoByRnc(emisorInfo.rnc());
        if (emisorInfo.razonSocial().equals(licenciaInfoDto.razonSocial()))
            throw new InvalidArgumentException("La razon social no coincide con la de la licencia");

        if (emisorInfo.direccionEmisor().equals(licenciaInfoDto.direccionFiscal()))
            throw new InvalidArgumentException("La direccion fiscal no coincide con la de la licencia");

        // Obtener sesión activa o crear si no existe
        SesionInfoDto sesionInfoDto = sesionProviderPort
                .obtenerSesionActiva(emisorInfo.rnc(), comprobante.ambienteEnum())
                .orElseGet(() -> {
                    try {
                        return sesionProviderPort.crear(emisorInfo.rnc(), comprobante.ambienteEnum());
                    } catch (Exception e) {
                        throw new RuntimeException("No se pudo crear la sesión", e);
                    }
                });


        return null;


    }
}

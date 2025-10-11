package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.SesionInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.LicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SesionProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In.EnviaComprobanteUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Encabezado.EmisorEncabezado;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidator;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidatorFactory.getValidator;

@Service
@RequiredArgsConstructor
public class EnviaComprobanteService implements EnviaComprobanteUseCase {

    private final LicenciaProviderPort licenciaProviderPort;
    private final SesionProviderPort sesionProviderPort;

    @Override
    public void execute(Comprobante comprobante) {

        // Obtiene una instancia de validacion segun el tipo de comprobante.
        ComprobanteValidator validator = getValidator(comprobante.tipoComprobanteTributarioEnum());

        // Valida segun la instancia obtenida.
        validator.execute(comprobante);

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





    }
}

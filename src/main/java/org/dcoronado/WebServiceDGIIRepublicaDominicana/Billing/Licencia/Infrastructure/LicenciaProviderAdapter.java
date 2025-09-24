package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.GetLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.LicenciaProviderPort;
import org.springframework.stereotype.Component;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Component
@RequiredArgsConstructor
public class LicenciaProviderAdapter implements LicenciaProviderPort {

    private final GetLicenciaUseCase getLicenciaUseCase;

    @Override
    public LicenciaInfoDto getLicencia(String rnc) {
        notBlank(rnc,"Rnc Requerido");
        Licencia licencia = getLicenciaUseCase.finByRnc(rnc);
        return new LicenciaInfoDto(
                licencia.getRutaCertificado(),
                licencia.getClaveCertificado(),
                licencia.getAmbiente()
        );
    }
}

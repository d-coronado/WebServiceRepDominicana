package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.GetLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.LicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Component;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Component
@RequiredArgsConstructor
public class LicenciaProviderAdapter implements LicenciaProviderPort {

    private final GetLicenciaUseCase getLicenciaUseCase;

    @Override
    public LicenciaInfoDto getLicenciaInfoByRnc(String rnc) {
        notBlank(rnc, "Rnc Requerido");

        return getLicenciaUseCase.finByRnc(rnc)
                .map(licencia -> new LicenciaInfoDto(
                        licencia.getRnc(),
                        licencia.getRutaCertificado(),
                        licencia.getClaveCertificado(),
                        licencia.getAmbiente(),
                        licencia.getRazonSocial(),
                        licencia.getDireccionFiscal()
                ))
                .orElseThrow(() -> new NotFoundException("Licencia con RNC " + rnc + " no encontrada"));
    }
}

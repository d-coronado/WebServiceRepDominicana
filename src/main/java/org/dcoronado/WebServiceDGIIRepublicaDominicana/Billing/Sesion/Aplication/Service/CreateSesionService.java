package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.LicenciaInfoPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SignPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSesionService implements CrearSesionUseCase {

    private final SignPort signProviderPort;
    private final LicenciaInfoPort licenciaInfoPort;
    private final GetSemillaDgiiProviderPort getSemillaDgiiProviderPort;
    private final ValidarSemillaDgiiProviderPort validarSemillaDgiiProvider;
    private final SesionRepositoryPort sesionRepositoryPort;

    @Override
    public Sesion crearSesion(Sesion sesion) throws Exception {
        sesion.validarParametrosGenericos();
        LicenciaInfoDto licenciaInfoDto = licenciaInfoPort.getLicenciaInfoByRnc(sesion.getRnc());
        sesion.validarAccesLimitAmbienteLicencia(licenciaInfoDto.limitAccessAmbiente());
        sesion.validarLicenciaRequireForSesion(licenciaInfoDto.pathCertificado(),licenciaInfoDto.claveCertificado());
        String semilla = getSemillaDgiiProviderPort.execute(sesion.getAmbiente());
        String semillaFirmada = signProviderPort.execute(semilla,licenciaInfoDto.pathCertificado(),licenciaInfoDto.claveCertificado());
        InfoTokenDgiiDto result = validarSemillaDgiiProvider.execute(sesion.getAmbiente(),semillaFirmada);
        sesion.setDatosSesion(result.token(),result.fechaExpedido(),result.fechaExpira());
        return sesionRepositoryPort.save(sesion);
    }
}

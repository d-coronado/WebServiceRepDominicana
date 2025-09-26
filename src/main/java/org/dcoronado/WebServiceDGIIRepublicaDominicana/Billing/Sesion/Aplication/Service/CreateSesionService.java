package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.LicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SignProviderPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSesionService implements CrearSesionUseCase {

    private final SignProviderPort signProviderPort;
    private final LicenciaProviderPort licenciaProviderPort;
    private final GetSemillaDgiiProviderPort getSemillaDgiiProviderPort;
    private final ValidarSemillaDgiiProviderPort validarSemillaDgiiProvider;
    private final SesionRepositoryPort sesionRepositoryPort;

    @Override
    public Sesion crearSesion(Sesion sesion) throws Exception {
        sesion.validarParametrosGenericos();
        LicenciaInfoDto licenciaInfoDto = licenciaProviderPort.getLicenciaInfoByRnc(sesion.getRnc());
        sesion.validarAccesLimitAmbienteLicencia(licenciaInfoDto.limitAccessAmbiente());
        sesion.validarLicenciaRequireForSesion(licenciaInfoDto.pathCertificado(),licenciaInfoDto.claveCertificado());
        String semilla = getSemillaDgiiProviderPort.execute(sesion.getAmbiente());
        String semillaFirmada = signProviderPort.execute(semilla,licenciaInfoDto.pathCertificado(),licenciaInfoDto.claveCertificado());
        InfoTokenDgiiDto result = validarSemillaDgiiProvider.execute(sesion.getAmbiente(),semillaFirmada);
        sesion.setDatosSesion(result.token(),result.fechaExpedido(),result.fechaExpira());
        return sesionRepositoryPort.save(sesion);
    }
}

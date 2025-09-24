package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto.InfoTokenDgiiDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Dto.LicenciaInfoDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSesionService implements CrearSesionUseCase {

    private final SignDocumentProviderPort signDocumentProviderPort;
    private final LicenciaProviderPort licenciaProviderPort;
    private final GetSemillaDgiiProviderPort getSemillaDgiiProviderPort;
    private final ValidarSemillaDgiiProviderPort validarSemillaDgiiProvider;
    private final SesionRepositoryPort sesionRepositoryPort;

    @Override
    public Sesion crearSesion(Sesion sesion) throws Exception {
        sesion.validarParametrosGenericos();
        LicenciaInfoDto licenciaInfoDto = licenciaProviderPort.getLicencia(sesion.getRnc());
        sesion.validarAccesLimitAmbienteLicencia(licenciaInfoDto.limitAccessAmbiente());
        sesion.validarLicenciaRequireForSesion(licenciaInfoDto.pathCertificado(),licenciaInfoDto.claveCertificado());
        String semilla = getSemillaDgiiProviderPort.execute(sesion.getAmbiente());
        String semillaFirmada = signDocumentProviderPort.execute(semilla,licenciaInfoDto.pathCertificado(),licenciaInfoDto.claveCertificado());
        InfoTokenDgiiDto result = validarSemillaDgiiProvider.execute(sesion.getAmbiente(),semillaFirmada);
        sesion.setDatosSesion(result.token(),result.fechaExpedido(),result.fechaExpira());
        return sesionRepositoryPort.save(sesion);
    }
}

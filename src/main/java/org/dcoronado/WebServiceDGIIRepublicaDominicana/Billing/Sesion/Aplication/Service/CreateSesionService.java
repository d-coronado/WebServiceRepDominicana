package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.GetLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ObtenerSemillaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In.ValidarSemillaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Sign.Aplication.SignDocumentUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSesionService implements CrearSesionUseCase {

    private final GetLicenciaUseCase getLicenciaUseCase;
    private final ObtenerSemillaUseCase obtenerSemillaUseCase;
    private final ValidarSemillaUseCase validarSemillaUseCase;
    private final SignDocumentUseCase signDocumentUseCase;

    @Override
    public Sesion crearSesion(Sesion sesion) throws Exception {
        sesion.validar();

        Licencia licencia = getLicenciaUseCase.finByRnc(sesion.getRnc());
        licencia.validarAccesoProduccion(sesion.getAmbiente());
        licencia.validarParametrosCertificadoDigital();

        String semilla = obtenerSemillaUseCase.obtenerSemilla(sesion.getAmbiente());
        String semillaFirmada = signDocumentUseCase.execute(semilla,licencia.getRutaCertificado(),licencia.getClaveCertificado());
        String result = validarSemillaUseCase.validarSemilla(sesion.getAmbiente(),semillaFirmada);

        return null;
    }
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.ManagerFileLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port.SaveFileLicenciaProvider;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoOperacionArchivoLicenciaEnum;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ManagerFileLicenciaProviderAdapter implements SaveFileLicenciaProvider {

    private final ManagerFileLicenciaUseCase saveFileLicenciaUseCase;

    @Override
    public void guardarArchivoSimple(String rnc, ContextoArchivoEnum contexto, String nombreArchivo, byte[] archivo) throws IOException {
        saveFileLicenciaUseCase.guardarArchivoSimple(rnc, contexto, nombreArchivo, archivo);
    }

    @Override
    public void guardarArchivoContexto(String rnc, ContextoArchivoEnum contexto, TipoOperacionArchivoLicenciaEnum tipoOperacion, AmbienteEnum ambiente, TipoComprobanteTributarioEnum tipoComprobante, String nombreArchivo, byte[] archivo) throws IOException {
        saveFileLicenciaUseCase.guardarArchivoContexto(rnc, contexto, tipoOperacion, ambiente, tipoComprobante, nombreArchivo, archivo);
    }
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.ManagerFileLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Contracts.Port.SaveFileLicenciaProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoOperacionArchivoLicenciaEnum;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ManagerFileLicenciaProviderAdapter implements SaveFileLicenciaProviderPort {

    private final ManagerFileLicenciaUseCase saveFileLicenciaUseCase;

    @Override
    public String guardarArchivoSimple(String rnc, ContextoArchivoEnum contexto, String nombreArchivo, byte[] archivo) throws IOException {
        return saveFileLicenciaUseCase.guardarArchivoSimple(rnc, contexto, nombreArchivo, archivo);
    }

    @Override
    public String guardarArchivoContexto(String rnc, ContextoArchivoEnum contexto, TipoOperacionArchivoLicenciaEnum tipoOperacion, AmbienteEnum ambiente, TipoComprobanteTributarioEnum tipoComprobante, String nombreArchivo, byte[] archivo) throws IOException {
        return saveFileLicenciaUseCase.guardarArchivoContexto(rnc, contexto, tipoOperacion, ambiente, tipoComprobante, nombreArchivo, archivo);
    }
}

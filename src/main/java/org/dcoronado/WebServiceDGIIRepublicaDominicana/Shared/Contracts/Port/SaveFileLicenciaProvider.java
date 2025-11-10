package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Contracts.Port;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoOperacionArchivoLicenciaEnum;

import java.io.IOException;

public interface SaveFileLicenciaProvider {
    void guardarArchivoSimple(
            String rnc,
            ContextoArchivoEnum contexto,
            String nombreArchivo,
            byte[] archivo
    ) throws IOException;

    void guardarArchivoContexto(
            String rnc,
            ContextoArchivoEnum contexto,
            TipoOperacionArchivoLicenciaEnum tipoOperacion,
            AmbienteEnum ambiente,
            TipoComprobanteTributarioEnum tipoComprobante,
            String nombreArchivo,
            byte[] archivo
    ) throws IOException;
}

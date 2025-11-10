package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoOperacionArchivoLicenciaEnum;

import java.io.IOException;

public interface ManagerFileLicenciaUseCase {

    void guardarArchivoSimple(String rnc, ContextoArchivoEnum contexto, String nombreArchivo, byte[] archivo) throws IOException;

    void guardarArchivoContexto(String rnc, ContextoArchivoEnum contexto, TipoOperacionArchivoLicenciaEnum tipoOperacion,
                                  AmbienteEnum ambiente, TipoComprobanteTributarioEnum tipoComprobante, String nombreArchivo, byte[] archivo) throws IOException;
}

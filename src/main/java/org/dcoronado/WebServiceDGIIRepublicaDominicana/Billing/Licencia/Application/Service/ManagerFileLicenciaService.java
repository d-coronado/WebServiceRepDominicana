package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.ManagerFileLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.DocumentFile;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.SaveFilePort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.ContextoArchivoEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoComprobanteTributarioEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoOperacionArchivoLicenciaEnum;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.RutasDirectoriosLicencia.*;

/**
 * Servicio de aplicación encargado de coordinar el guardado de archivos
 * asociados a una licencia, respetando la estructura de directorios definida
 * en el dominio y validando que la licencia tenga los directorios configurados.
 */
@Service
@RequiredArgsConstructor
public class ManagerFileLicenciaService implements ManagerFileLicenciaUseCase {

    private final SaveFilePort saveFilePort;
    private final LicenciaRepositoryPort licenciaRepositoryPort;

    /**
     * Guarda un archivo simple en contextos que no requieren clasificación adicional
     * (certificado digital, otros, etc.)
     *
     * @param rnc           RNC de la licencia
     * @param contexto      contexto del archivo (CERTIFICADO_DIGITAL, OTRO)
     * @param nombreArchivo nombre del archivo a guardar
     * @param archivo       contenido binario del archivo
     * @throws NotFoundException        si no se encuentra la licencia
     * @throws InvalidArgumentException si el contexto no es válido o los directorios no están configurados
     */
    @Override
    public void guardarArchivoSimple(String rnc, ContextoArchivoEnum contexto, String nombreArchivo, byte[] archivo) {
        validar(rnc, nombreArchivo, archivo);
        validarContextoSimple(contexto);
        final String rutaRelativa = construirRutaSimple(rnc, contexto, nombreArchivo);
        final String rutaBase = saveFilePort.getBasePath();
        final String rutaCompleta = rutaBase.concat(rutaRelativa);
        saveFilePort.save(rutaCompleta, archivo);
    }


    /**
     * Guarda un archivo de comprobante o aprobación comercial con toda su clasificación
     * (tipo de operación, ambiente, tipo de comprobante).
     *
     * @param rnc             RNC de la licencia
     * @param contexto        contexto del archivo (COMPROBANTE o APROBACION_COMERCIAL)
     * @param tipoOperacion   tipo de operación (EMISION o RECEPCION)
     * @param ambiente        ambiente (PRUEBAS, CERTIFICACION, PRODUCCION)
     * @param tipoComprobante tipo de comprobante tributario
     * @param nombreArchivo   nombre del archivo a guardar
     * @param archivo         contenido binario del archivo
     * @throws IOException              si ocurre un error al guardar el archivo
     * @throws NotFoundException        si no se encuentra la licencia
     * @throws InvalidArgumentException si el contexto no es válido o los directorios no están configurados
     */
    @Override
    public void guardarArchivoContexto(String rnc, ContextoArchivoEnum contexto, TipoOperacionArchivoLicenciaEnum tipoOperacion,
                                       AmbienteEnum ambiente, TipoComprobanteTributarioEnum tipoComprobante, String nombreArchivo, byte[] archivo) throws IOException {

        validar(rnc, nombreArchivo, archivo);
        validarContextoComprobante(contexto);
        String rutaRelativa = String.join("/", getRutaArchivoLicencia(rnc, contexto, tipoOperacion, ambiente, tipoComprobante), nombreArchivo);
        final String rutaBase = saveFilePort.getBasePath();
        final String rutaCompleta = rutaBase.concat(rutaRelativa);
        saveFilePort.save(rutaCompleta, archivo);
    }

    private void validar(String rnc, String nombreArchivo, byte[] archivo) {

        RNC rncValueObject = RNC.of(rnc);
        DocumentFile.of(nombreArchivo, archivo);

        Licencia licenciaSaved = licenciaRepositoryPort.findByRnc(rncValueObject.getValor())
                .orElseThrow(() -> new NotFoundException("Licencia con RNC " + rncValueObject.getValor() + " no encontrada"));

        if (!licenciaSaved.tieneSetupDirectoriosCompletado()) {
            throw new InvalidArgumentException(
                    "No se puede guardar el archivo porque la creación de directorios aún está pendiente. " +
                            "Estado actual: %s".formatted(licenciaSaved.getDirectoriesSetupStatus())
            );
        }
    }

    private void validarContextoSimple(ContextoArchivoEnum contexto) {
        if (contexto == ContextoArchivoEnum.COMPROBANTE ||
                contexto == ContextoArchivoEnum.APROBACION_COMERCIAL) {
            throw new InvalidArgumentException(
                    "Para contexto %s debe usar guardarArchivoComprobante()".formatted(contexto)
            );
        }
    }

    private void validarContextoComprobante(ContextoArchivoEnum contexto) {
        if (contexto != ContextoArchivoEnum.COMPROBANTE &&
                contexto != ContextoArchivoEnum.APROBACION_COMERCIAL) {
            throw new InvalidArgumentException(
                    "Contexto debe ser COMPROBANTE o APROBACION_COMERCIAL, recibido: " + contexto
            );
        }
    }

    private String construirRutaSimple(String rnc, ContextoArchivoEnum contexto, String nombreArchivo) {
        return switch (contexto) {
            case CERTIFICADO_DIGITAL -> String.join("/", getRutaCertificadoDigital(rnc), nombreArchivo);
            case OTRO -> String.join("/", getRutaOtros(rnc), nombreArchivo);
            default -> throw new InvalidArgumentException(
                    "Contexto no válido para archivo simple: " + contexto
            );
        };
    }
}
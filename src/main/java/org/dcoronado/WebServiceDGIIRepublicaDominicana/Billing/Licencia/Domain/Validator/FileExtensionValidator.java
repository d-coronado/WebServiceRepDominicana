package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;

import java.util.List;

public class FileExtensionValidator {

    /* Solo se aceptaran archivos con extension .xml */
    public static void validateExtensionXml(String nombreDocumento) {
        List<String> extensionesPermitidas = List.of("xml");
        String extension = nombreDocumento.contains(".")
                ? nombreDocumento.substring(nombreDocumento.lastIndexOf('.') + 1).toLowerCase()
                : "";
        if (!extensionesPermitidas.contains(extension))
            throw new InvalidArgumentException("Extensión no permitida, se requiere .xml");
    }

    /* Solo se aceptarán archivos con extensión .xls o .xlsx */
    public static void validateExtensionExcel(String nombreDocumento) {
        List<String> extensionesPermitidas = List.of("xls", "xlsx");
        String extension = nombreDocumento.contains(".")
                ? nombreDocumento.substring(nombreDocumento.lastIndexOf('.') + 1).toLowerCase()
                : "";
        if (!extensionesPermitidas.contains(extension)) {
            throw new InvalidArgumentException("Extensión no permitida, se requiere .xls o .xlsx");
        }
    }
}

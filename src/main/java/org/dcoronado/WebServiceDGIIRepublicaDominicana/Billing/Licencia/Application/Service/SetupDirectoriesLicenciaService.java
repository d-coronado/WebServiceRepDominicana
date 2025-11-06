package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.SetupDirectoriesLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.SetupDirectoriesPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Model.TreeNode;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioLicenciaTreeBuilder.buildLicenciaTree;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

/**
 * Servicio encargado de preparar y crear la estructura de directorios asociada a una licencia.
 * Este proceso incluye la validación del RNC, verificación del estado del setup,
 * construcción del árbol de directorios y persistencia del cambio en el repositorio.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SetupDirectoriesLicenciaService implements SetupDirectoriesLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final SetupDirectoriesPort setupDirectoriesPort;


    /**
     * Ejecuta el proceso de creación y configuración de directorios
     * para una licencia asociada al RNC proporcionado.
     * @param rnc RNC de la licencia para la cual se realizará el setup de directorios. No debe ser nulo ni vacío.
     * @throws InvalidArgumentException si el RNC es inválido o si el setup ya fue completado anteriormente.
     * @throws RuntimeException si no se encuentra una licencia asociada al RNC indicado.
     */
    @Override
    public void execute(final String rnc) {
        log.info("INICIO - Proceso de setup de directorios para licencia con RNC {}", rnc);

        // Validar parámetro de entrada
        log.info("[1] Validando datos de entrada");
        notBlank(rnc, "rnc required");

        // Busca la licencia
        log.info("[2] Buscando licencia por RNC {}", rnc);
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new RuntimeException("Licencia not found"));

        // Validar que el setup NO esté completado
        log.info("[3] Verificando estado de setup de directorios para licencia con RNC {}", rnc);
        if (licencia.tieneSetupDirectoriosCompletado()) {
            throw new InvalidArgumentException(
                    "El setup de directorios ya fue completado para RNC %s en %s"
                            .formatted(rnc, licencia.getDatabaseSetupAt()));
        }

        // Armar estructura de directorios
        log.info("[4] Armando arbol de directorios para licencia con RNC {}", rnc);
        TreeNode directoryTreeLicencia = buildLicenciaTree(licencia.getRnc());

        // Crear estructura de directorios
        log.info("[5] Persistiendo arbol de directorios para licencia con RNC {}", rnc);
        setupDirectoriesPort.createDirectory(directoryTreeLicencia);

        log.info("[6] Marcando setup de directorios como completado para licencia con RNC {}", rnc);
        licencia.marcarSetupDirectoriosCompletado();

        log.info("[7] Guardando cambios en el repositorio de licencia con RNC {}", rnc);
        licenciaRepositoryPort.save(licencia);
    }
}

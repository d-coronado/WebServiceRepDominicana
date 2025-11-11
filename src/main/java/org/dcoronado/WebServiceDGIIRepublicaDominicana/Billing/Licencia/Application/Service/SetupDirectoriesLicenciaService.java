package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.SetupDirectoriesLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.SetupDirectoriesPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.ValueObject.RNC;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.TreeNode;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioLicenciaTreeBuilder.buildLicenciaTree;

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
     * @throws NotFoundException si no se encuentra una licencia asociada al RNC indicado.
     */
    @Transactional
    @Override
    public void execute(final String rnc) {
        log.info("INICIO - Proceso de setup de directorios para licencia con RNC {}", rnc);

        RNC rncValueObject = RNC.of(rnc);

        // Busca la licencia
        log.info("[1] Buscando licencia por RNC {}", rncValueObject);
        Licencia licencia = licenciaRepositoryPort.findByRnc(rncValueObject.getValor())
                .orElseThrow(() -> new NotFoundException("Licencia not found"));

        licencia.prepararSetupDirectorios();

        // Armar estructura de directorios
        log.info("[3] Armando arbol de directorios para licencia con RNC {}", rncValueObject);
        TreeNode directoryTreeLicencia = buildLicenciaTree(licencia.getRnc().getValor());

        // Crear estructura de directorios
        log.info("[4] Persistiendo arbol de directorios para licencia con RNC {}", rncValueObject);
        setupDirectoriesPort.createDirectory(directoryTreeLicencia);

        log.info("[5] Guardando cambios en el repositorio de licencia con RNC {}", rncValueObject);
        licenciaRepositoryPort.save(licencia);
    }
}

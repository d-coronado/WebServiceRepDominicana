package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.SetupDirectoriesLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.SetupDirectoriesPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioNode;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.springframework.stereotype.Service;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.DirectorioTreeBuilder.buildLicenciaTree;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Service
@RequiredArgsConstructor
public class SetupDirectoriesLicenciaService implements SetupDirectoriesLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;
    private final SetupDirectoriesPort setupDirectoriesPort;


    @Override
    public void execute(String rnc) {
        notBlank(rnc, "rnc required");

        // Busca la licencia
        Licencia licencia = licenciaRepositoryPort.findByRnc(rnc)
                .orElseThrow(() -> new RuntimeException("Licencia not found"));

        // Validar que el setup NO esté completado
        if (licencia.tieneSetupDirectoriosCompletado()) {
            throw new InvalidArgumentException(
                    "El setup de directorios ya fue completado para RNC %s en %s"
                            .formatted(rnc, licencia.getDatabaseSetupAt()));
        }


        /* Crear estructura de directorios */
        DirectorioNode directoryTreeLicencia = buildLicenciaTree(licencia.getRnc());
        setupDirectoriesPort.createDirectory(directoryTreeLicencia);

        licencia.marcarSetupDirectoriosCompletado();

        licenciaRepositoryPort.save(licencia);
    }
}

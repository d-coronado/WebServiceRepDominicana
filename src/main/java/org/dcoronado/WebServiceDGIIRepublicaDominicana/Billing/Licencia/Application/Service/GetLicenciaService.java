package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.GetLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetLicenciaService implements GetLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;

    @Override
    public Licencia findById(Long id) {
        Optional<Licencia> existingLicencia = licenciaRepositoryPort.findById(id);
        if(existingLicencia.isEmpty()) throw new NotFoundException("Licencia con ID " + id + " no encontrada");
        return existingLicencia.get();
    }

    @Override
    public Licencia finByRnc(String rnc) {
        Optional<Licencia> existingLicencia = licenciaRepositoryPort.findByRnc(rnc);
        if(existingLicencia.isEmpty()) throw new NotFoundException("Licencia con RNC " + rnc + " no encontrada");
        return existingLicencia.get();
    }
}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Service;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.GetLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetLicenciaService implements GetLicenciaUseCase {

    private final LicenciaRepositoryPort licenciaRepositoryPort;

    @Override
    public Optional<Licencia> findById(Long id) {
        return licenciaRepositoryPort.findById(id);
    }

    @Override
    public Optional<Licencia> finByRnc(String rnc) {
        return licenciaRepositoryPort.findByRnc(rnc);
    }
}

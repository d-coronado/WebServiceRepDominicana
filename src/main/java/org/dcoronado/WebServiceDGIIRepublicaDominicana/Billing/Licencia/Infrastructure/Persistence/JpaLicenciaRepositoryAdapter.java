package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Persistence;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.LicenciaRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaLicenciaRepositoryAdapter implements LicenciaRepositoryPort {

    private final SpringDataLicenciaRepository springDataLicenciaRepository;
    private final LicenciaMapper licenciaMapper;

    @Override
    public Licencia save(Licencia licencia) {
        LicenciaEntity licenciaEntity = licenciaMapper.toEntity(licencia);
        final LicenciaEntity savedLicencia = springDataLicenciaRepository.save(licenciaEntity);
        return licenciaMapper.toDomain(savedLicencia);
    }


    @Override
    public Optional<Licencia> findById(Long id) {
        return springDataLicenciaRepository.findById(id)
                .map(licenciaMapper::toDomain);
    }

    @Override
    public Optional<Licencia> findByRnc(String rnc) {
        return springDataLicenciaRepository.findByRnc(rnc)
                .map(licenciaMapper::toDomain);
    }

}

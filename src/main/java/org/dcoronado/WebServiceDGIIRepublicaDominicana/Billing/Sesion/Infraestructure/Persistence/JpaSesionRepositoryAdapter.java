package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Persistence;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.Out.SesionRepositoryPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaSesionRepositoryAdapter implements SesionRepositoryPort {

    private final SpringDataSesionRepository springDataSesionRepository;
    private final SesionMapper sesionMapper;

    @Override
    public Sesion save(Sesion sesion) {
        SesionEntity sesionEntity = sesionMapper.toEntity(sesion);
        final SesionEntity savedSesion = springDataSesionRepository.save(sesionEntity);
        return sesionMapper.toDomain(savedSesion);
    }

    @Override
    public Optional<Sesion> findSesionActiveByRnc(Sesion sesion, LocalDateTime ahora) {
        return springDataSesionRepository.findSesionActivaByRnc(sesion.getRnc(), sesion.getAmbiente().getCodigo(), ahora).map(sesionMapper::toDomain);
    }
}

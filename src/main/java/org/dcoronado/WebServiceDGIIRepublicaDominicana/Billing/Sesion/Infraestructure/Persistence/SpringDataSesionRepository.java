package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Persistence;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SpringDataSesionRepository extends JpaRepository<SesionEntity, Long> {

    String FIND_SESION_ACTIVA = """
            SELECT s
            FROM SesionEntity s
            WHERE s.rnc = :rnc
              AND s.ambiente = :ambiente
              AND s.expira > :ahora
            ORDER BY s.expedido DESC
            """;

    @Query(FIND_SESION_ACTIVA)
    Optional<SesionEntity> findSesionActivaByRnc(
            @Param("rnc") String rnc,
            @Param("ambiente") AmbienteEnum ambiente,
            @Param("ahora") LocalDateTime ahora
    );
}

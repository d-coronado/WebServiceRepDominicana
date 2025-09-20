package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpringDataSesionRepository extends JpaRepository<SesionEntity, Long> {

    String SQL_SELECT_SESION_TOKEN_60_MIN = "SELECT e.* FROM sesionempresa e WHERE e.sesionempresa_rnc=:rnc  AND e.sesionempresa_ambiente=:ambiente AND TIMESTAMPDIFF(MINUTE, e.sesionempresa_fecharegistro, DATE_ADD(NOW(), INTERVAL 1 HOUR)) <= 60 ORDER BY e.sesionempresa_id DESC LIMIT 1";

    @Query(value = SQL_SELECT_SESION_TOKEN_60_MIN, nativeQuery = true)
    Optional<SesionEntity> findSesionActivaByRnc(@Param("rnc") String rnc, @Param("ambiente") String ambiente);
}

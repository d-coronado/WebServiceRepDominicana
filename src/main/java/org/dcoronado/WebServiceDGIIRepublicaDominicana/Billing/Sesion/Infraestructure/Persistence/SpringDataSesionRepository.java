package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Persistence;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SpringDataSesionRepository extends JpaRepository<SesionEntity, Long> {

    String FIND_SESION_ACTIVA = "SELECT s.* FROM sesionempresa s WHERE s.sesionempresa_rnc = :rnc AND s.sesionempresa_ambiente = :ambienteEnum AND s.sesionempresa_tokenexpira > :ahora ORDER BY s.sesionempresa_tokenexpedido DESC LIMIT 1";

    @Query(value = FIND_SESION_ACTIVA, nativeQuery = true)
    Optional<SesionEntity> findSesionActivaByRnc(@Param("rnc") String rnc, @Param("ambienteEnum") AmbienteEnum ambiente, @Param("ahora") LocalDateTime ahora);
}

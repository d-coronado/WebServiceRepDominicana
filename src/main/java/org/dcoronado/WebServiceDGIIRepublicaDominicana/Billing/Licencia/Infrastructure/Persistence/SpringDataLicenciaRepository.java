package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface SpringDataLicenciaRepository extends JpaRepository<LicenciaEntity, Long> {
    @Query("SELECT l FROM LicenciaEntity l WHERE l.rnc = :rncLicencia")
    Optional<LicenciaEntity> findByRnc(@Param("rncLicencia") String rnc);
}

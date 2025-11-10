package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "SesionEntity")
@Table(name = "sesionempresa")
public class SesionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sesionempresa_id")
    private Long id;

    @Column(name = "sesionempresa_rnc")
    private String rnc;

    @Enumerated(EnumType.STRING)
    @Column(name = "sesionempresa_ambiente")
    private AmbienteEnum ambiente;

    @Column(name = "sesionempresa_token", columnDefinition = "TEXT")
    private String token;

    /**
     * Guardado siempre en UTC.
     */
    @Column(name = "sesionempresa_tokenexpedido")
    private LocalDateTime expedido;

    /**
     * Guardado siempre en UTC.
     */
    @Column(name = "sesionempresa_tokenexpira")
    private LocalDateTime expira;

    @Column(name = "sesionempresa_fecharegistro", nullable = false)
    private LocalDateTime fechaRegitro;

    @PrePersist
    public void prePersist() {
        fechaRegitro = LocalDateTime.now();
    }
}

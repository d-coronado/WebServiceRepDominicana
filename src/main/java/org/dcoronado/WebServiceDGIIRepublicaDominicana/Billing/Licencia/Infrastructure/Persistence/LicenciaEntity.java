package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "licencia")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LicenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @Column(name = "rnc", unique = true)
    private String rnc;

    @Column(name = "razonsocial", nullable = false)
    private String razonSocial;

    @Column(name = "direccion_fiscal", nullable = false)
    private String direccionFiscal;

    @Column(name = "alias")
    private String alias;

    @Column(name = "nombre_contacto")
    private String nombreContacto;

    @Column(name = "telefono_contacto")
    private String telefonoContacto;

    @Column(name = "nombre_certificado")
    private String nombreCertificado;

    @Column(name = "password_certificado")
    private String claveCertificado;

    @Column(name = "ruta_certificado")
    private String rutaCertificado;

    @Column(name = "host_bd")
    private String hostBd;

    @Column(name = "puerto_bd")
    private String puertoBd;

    @Column(name = "urlconexion_bd")
    private String urlConexionBd;

    @Column(name = "nombre_bd")
    private String nombreBd;

    @Column(name = "usuario_bd")
    private String usuarioBd;

    @Column(name = "password_bd")
    private String passwordBd;

    @Column(name = "ambiente")
    private String ambiente;

    @Column(name = "is_active")
    private Boolean isActive;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        isActive = true;
    }

}

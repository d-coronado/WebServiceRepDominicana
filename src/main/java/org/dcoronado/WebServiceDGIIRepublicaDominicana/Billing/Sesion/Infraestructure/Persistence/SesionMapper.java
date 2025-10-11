package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Persistence;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

@Component
public class SesionMapper {
    public SesionEntity toEntity(Sesion sesion) {
        if (sesion == null) return null;
        SesionEntity sesionEntity = new SesionEntity();
        sesionEntity.setId(sesion.getId());
        sesionEntity.setRnc(sesion.getRnc());
        sesionEntity.setAmbiente(sesion.getAmbiente());
        sesionEntity.setToken(sesion.getToken());
        sesionEntity.setExpedido(sesion.getExpedido().toLocalDateTime()); // Convertimos UTC a LocalDateDime
        sesionEntity.setExpira(sesion.getExpira().toLocalDateTime());
        return sesionEntity;
    }

    public Sesion toDomain(SesionEntity sesionEntity) {
        if (sesionEntity == null) return null;
        Sesion sesion = new Sesion();
        sesion.setId(sesionEntity.getId());
        sesion.setRnc(sesionEntity.getRnc());
        sesion.setAmbiente(sesionEntity.getAmbiente());
        sesion.setToken(sesionEntity.getToken());
        sesion.setExpedido(sesionEntity.getExpedido().atOffset(ZoneOffset.UTC));  // Recuperamos como LocalDateTime y lo envolvemos como UTC explícito
        sesion.setExpira(sesionEntity.getExpira().atOffset(ZoneOffset.UTC));  // Recuperamos como LocalDateTime y lo envolvemos como UTC explícito
        return sesion;
    }
}

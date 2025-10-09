package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.GetSesionActivaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain.Sesion;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Factory.SesionFactory;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Request.SesionRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Response.SesionResponseDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.Dto.Transformer.SesionTransformer;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Response.CustomResponse;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.Api.AbstractApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2/sesion")
public class SesionController extends AbstractApi {

    private final CrearSesionUseCase sesionUseCase;
    private final GetSesionActivaUseCase getSesionActivaUseCase;
    private final SesionFactory sesionFactory;
    private final SesionTransformer sesionTransformer;

    @PostMapping("/crear")
    public ResponseEntity<CustomResponse> crearSesion(@Valid @RequestBody SesionRequestDto sesionRequestDto) throws Exception {
        Sesion sesion = sesionFactory.ofDto(sesionRequestDto);
        Sesion result = sesionUseCase.crearSesion(sesion);
        SesionResponseDto responseDto = sesionTransformer.fromObject(result);
        return success(responseDto);
    }

    @PostMapping("/obtener_activa")
    public ResponseEntity<CustomResponse> getSesionActiva(@Valid @RequestBody SesionRequestDto sesionRequestDto) {
        Sesion sesion = sesionFactory.ofDto(sesionRequestDto);
        return getSesionActivaUseCase.getSesionActiva(sesion)
                .map(s -> {
                    SesionResponseDto responseDto = sesionTransformer.fromObject(s);
                    return success(responseDto);
                })
                .orElseGet(() -> {
                    LocalDateTime ahoraUtc = LocalDateTime.now(ZoneOffset.UTC);
                    String msg = "No hay sesiones activas para la fecha / hora actual (UTC): " + ahoraUtc;
                    return success(msg);
                });

    }

}
package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Infraestructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Aplication.Port.In.CrearSesionUseCase;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v2/sesion")
public class SesionController extends AbstractApi {

    private final CrearSesionUseCase sesionUseCase;
    private final SesionFactory sesionFactory;
    private final SesionTransformer sesionTransformer;

    @PostMapping("/crear")
    public ResponseEntity<CustomResponse> crearSesion(@Valid @RequestBody SesionRequestDto sesionRequestDto) throws Exception {
        Sesion sesion = sesionFactory.ofDto(sesionRequestDto);
        Sesion result = sesionUseCase.crearSesion(sesion);
        SesionResponseDto responseDto = sesionTransformer.fromObject(result);
        return success(responseDto);
    }

}
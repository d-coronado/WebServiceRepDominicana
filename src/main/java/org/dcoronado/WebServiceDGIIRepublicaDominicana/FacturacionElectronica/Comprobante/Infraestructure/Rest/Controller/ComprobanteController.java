package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In.EnviaComprobanteDgiiUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Factory.ComprobanteFactory;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.ComprobanteGenericRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Response.CustomResponse;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.Api.AbstractApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v2/comprobante")
@RequiredArgsConstructor
public class ComprobanteController extends AbstractApi {

    private final ComprobanteFactory comprobanteFactory;
    private final EnviaComprobanteDgiiUseCase enviaComprobanteDgiiUseCase;


    @PostMapping("/envia_dgii")
    public ResponseEntity<CustomResponse> EnviaComprobanteDgii(@Valid @RequestBody ComprobanteGenericRequestDto comprobanteGenericRequestDto) {
        Comprobante comprobante = comprobanteFactory.ofDto(comprobanteGenericRequestDto); // DTO → Domain
        Comprobante result = enviaComprobanteDgiiUseCase.execute(comprobante);
        return success("Comprobante Enviado Exitosamente");

    }

}

package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Application.In.EnviaComprobanteUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Comprobante;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Factory.ComprobanteFactory;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Request.ComprobanteGenericRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Response.ComprobanteResponse;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Infraestructure.Rest.Dto.Transformer.ComprobanteDtoTransformer;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Response.CustomResponse;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.Api.AbstractApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v2/comprobante")
@Tag(name = "Comprobante")
@RequiredArgsConstructor
public class ComprobanteController extends AbstractApi {

    private final ComprobanteFactory comprobanteFactory;
    private final EnviaComprobanteUseCase enviaComprobanteDgiiUseCase;
    private final ComprobanteDtoTransformer comprobanteDtoTransformer;


    @Operation(summary = "Envia Comprobante", description = "Envia un comprobante electrónico a la DGII")
    @PostMapping("/envia")
    public ResponseEntity<CustomResponse> EnviaComprobanteDgii(@Valid @RequestBody ComprobanteGenericRequestDto comprobanteGenericRequestDto) throws Exception {
        Comprobante comprobante = comprobanteFactory.ofDto(comprobanteGenericRequestDto); // DTO → Domain
        Comprobante result = enviaComprobanteDgiiUseCase.execute(comprobante);
        ComprobanteResponse responseDto = comprobanteDtoTransformer.fromObject(result); // Domain → DTO
        return success(responseDto);
    }

}

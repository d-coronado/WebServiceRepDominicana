package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.CreateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.GetLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UpdateLicenciaUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.UploadCertificadoUseCase;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Factory.LicenciaFactory;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Request.LicenciaRequestDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Response.LicenciaResponseDto;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Dto.Transformer.LicenciaDtoTransformer;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Infraestructure.Api.AbstractApi;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(path = "/api/v2/licencia")
@RequiredArgsConstructor
public class LicenciaController extends AbstractApi {

    private final CreateLicenciaUseCase createLicenciaUseCase;
    private final UpdateLicenciaUseCase updateLicenciaUseCase;
    private final GetLicenciaUseCase getLicenciaUseCase;
    private final UploadCertificadoUseCase uploadCertificadoUseCase;
    private final LicenciaFactory licenciaFactory;
    private final LicenciaDtoTransformer licenciaDtoTransformer;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@Valid @RequestBody LicenciaRequestDto request)
    {
        Licencia licencia = licenciaFactory.ofDto(request); // DTO → Domain
        Licencia result = createLicenciaUseCase.createLicencia(licencia); // Ejecutar caso de uso
        LicenciaResponseDto responseDto = licenciaDtoTransformer.fromObject(result); // Domain → DTO
        return success(responseDto,"Licencia Creada Exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> update(@PathVariable("id") final Long id, @RequestBody LicenciaRequestDto request){
        Licencia licencia = licenciaFactory.fromDtoForUpdate(id,request);
        Licencia result = updateLicenciaUseCase.updateLicencia(licencia);
        LicenciaResponseDto responseDto = licenciaDtoTransformer.fromObject(result);
        return success(responseDto,"Licencia Actualizada Exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getLicenciaById(@PathVariable("id") final Long id)
    {
        final Licencia licencia = getLicenciaUseCase.findById(id);
        LicenciaResponseDto responseDto = licenciaDtoTransformer.fromObject(licencia);
        return success(responseDto);
    }

    @GetMapping("/rnc/{rnc}")
    public ResponseEntity<CustomResponse> getLicenciaByRnc(@PathVariable("rnc") final String rnc)
    {
        final Licencia licencia = getLicenciaUseCase.finByRnc(rnc);
        LicenciaResponseDto responseDto = licenciaDtoTransformer.fromObject(licencia);
        return success(responseDto);
    }

    @PostMapping("/subir_certificado/{rnc}")
    public ResponseEntity<CustomResponse> uploadCertificadoDigital(
            @Valid @PathVariable("rnc") final String rnc,@RequestParam("archivo") final MultipartFile archivo,
            @RequestParam("nombreArchivo") final String nombreArchivo,
            @RequestParam("contrasenia") final String contrasenia
    ) throws IOException {
        uploadCertificadoUseCase.execute(rnc,archivo.getBytes(),nombreArchivo,contrasenia);
        return success("Certificado cargado correctamente para la licencia con RNC: " + rnc);
    }
}

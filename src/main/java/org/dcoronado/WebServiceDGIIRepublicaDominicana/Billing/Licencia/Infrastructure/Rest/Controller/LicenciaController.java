package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure.Rest.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.In.*;
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
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping(path = "/api/v2/licencia")
@RequiredArgsConstructor
public class LicenciaController extends AbstractApi {

    private final CreateLicenciaUseCase createLicenciaUseCase;
    private final UpdateLicenciaUseCase updateLicenciaUseCase;
    private final GetLicenciaUseCase getLicenciaUseCase;
    private final UploadCertificadoUseCase uploadCertificadoUseCase;
    private final FirmarDocumentUseCase firmarDocumentByLicenciaUseCase;
    private final SetupDatabaseLicenciaUseCase setupDatabaseLicenciaUseCase;
    private final SetupDirectoriesLicenciaUseCase setupDirectoriesLicenciaUseCase;
    private final LicenciaFactory licenciaFactory;
    private final LicenciaDtoTransformer licenciaDtoTransformer;

    @PostMapping
    public ResponseEntity<CustomResponse> save(@Valid @RequestBody LicenciaRequestDto request) {
        Licencia licencia = licenciaFactory.ofDto(request); // DTO → Domain
        Licencia result = createLicenciaUseCase.createLicencia(licencia); // Ejecutar caso de uso
        LicenciaResponseDto responseDto = licenciaDtoTransformer.fromObject(result); // Domain → DTO
        return success(responseDto, "Licencia Creada Exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> update(@PathVariable("id") final Long id, @Valid @RequestBody LicenciaRequestDto request) {
        Licencia licencia = licenciaFactory.fromDtoForUpdate(id, request);
        Licencia result = updateLicenciaUseCase.updateLicencia(licencia);
        LicenciaResponseDto responseDto = licenciaDtoTransformer.fromObject(result);
        return success(responseDto, "Licencia Actualizada Exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getLicenciaById(@PathVariable("id") final Long id) {
        return getLicenciaUseCase.findById(id)
                .map(l -> {
                    LicenciaResponseDto responseDto = licenciaDtoTransformer.fromObject(l);
                    return success(responseDto);
                })
                .orElseGet(() -> success("Licencia con id: " + id + " no encontrada"));
    }

    @GetMapping("/rnc/{rnc}")
    public ResponseEntity<CustomResponse> getLicenciaByRnc(@PathVariable("rnc") final String rnc) {
        return getLicenciaUseCase.finByRnc(rnc)
                .map(l -> {
                    LicenciaResponseDto responseDto = licenciaDtoTransformer.fromObject(l);
                    return success(responseDto);
                })
                .orElseGet(() -> success("Licencia con RNC: " + rnc + " no encontrada"));
    }

    /**
     * Setup de base de datos (solo una vez)
     */
    @PostMapping("/{rnc}/setup-database")
    public ResponseEntity<CustomResponse> setupDatabase(@PathVariable String rnc) {
        setupDatabaseLicenciaUseCase.execute(rnc);
        return success("Setup database creado correctamente");
    }


    @PostMapping("/{rnc}/setup-directories")
    public ResponseEntity<CustomResponse> setupDirectories(@PathVariable String rnc) {
        setupDirectoriesLicenciaUseCase.execute(rnc);
        return success("Setup directories creado correctamente");
    }

    @PostMapping("/subir_certificado/{rnc}")
    public ResponseEntity<CustomResponse> uploadCertificadoDigital(
            @PathVariable("rnc") final String rnc, @RequestParam("archivo") final MultipartFile archivo,
            @RequestParam("contrasenia") final String contrasenia
    ) throws IOException {
        String nombreArchvio = archivo.getOriginalFilename();
        byte[] contenido = archivo.getBytes();
        uploadCertificadoUseCase.execute(rnc, nombreArchvio, contenido, contrasenia);
        return success("Certificado cargado correctamente para la licencia con RNC: " + rnc);
    }

    @PostMapping("firmar_documento/{rnc}")
    public ResponseEntity<CustomResponse> signDocument(@PathVariable("rnc") final String rnc,
                                                       @RequestParam("archivo") final MultipartFile archivo) throws Exception {
        String nombreArchvio = archivo.getOriginalFilename();
        byte[] contenido = archivo.getBytes();
        String documentFirmado = firmarDocumentByLicenciaUseCase.firmarDocumentByLicencia(rnc, nombreArchvio, contenido);
        String documentoBase64 = Base64.getEncoder().encodeToString(documentFirmado.getBytes(StandardCharsets.UTF_8));
        return success(documentoBase64);
    }

}

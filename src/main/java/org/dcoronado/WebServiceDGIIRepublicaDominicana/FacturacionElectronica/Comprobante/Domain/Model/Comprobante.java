package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.Encabezado;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteBaseValidator;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteEspecificoValidator;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidatorFactory;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoComprobanteTributarioEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.ComprobanteConstantes.UMBRAL_FACTURA_CONSUMO_RESUMIDA;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.ComprobanteConstantes.VERSION_FORMATO;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.required;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Util.StringToValUtil.rellenarConCeros;


@Getter
@Setter
@Builder
public class Comprobante {

    // Datos de entrada
    private final Encabezado encabezado;
    private final List<Item> items;
    private final List<SubTotal> subtotales;
    private final List<DescuentoORecargo> descuentosORecargos;
    private final List<PaginaSubTotal> paginas;
    private final InformacionReferencia informacionReferencia;

    // Datos que se generan despues de validar la entrada.
    private String fechaHoraFirma;
    private String encf;
    private String hashFirmaComprobanteExtendido;
    private String codigoSeguridad;
    private boolean esResumen;


    public void validar() {
        ComprobanteBaseValidator.execute(this);
    }

    public void validarSegunTipoComprobante(TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum) {
        required(tipoComprobanteTributarioEnum, "TipoComprobanteTributarioEnum es requerido para validaciones específicas");

        // Devuelve una instacia segun el tipo de comprobante y hace sus validaciones especificas.
        ComprobanteEspecificoValidator comprobanteEspecificoValidator = ComprobanteValidatorFactory.getValidator(tipoComprobanteTributarioEnum);
        comprobanteEspecificoValidator.execute(this);
    }

    public void enriquecer(TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum, String secuencia) {
        construirEncf(tipoComprobanteTributarioEnum, secuencia);
        this.fechaHoraFirma = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.encabezado.setVersion(VERSION_FORMATO);
        redondearCalculos();
    }

    private void construirEncf(final TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum,final String secuencia) {
        required(tipoComprobanteTributarioEnum, "TipoComprobanteTributarioEnum es requerido para construir encf");
        required(secuencia," Secuencia es requerida para construir encf");

        final String secuenciaCompleta = rellenarConCeros(secuencia, 10);
        this.encf = "E".concat(tipoComprobanteTributarioEnum.getValor().toString()).concat(secuenciaCompleta);
    }

    // Este metodo deberia redondear a dos decimales todos los valores del comprobante.
    private void redondearCalculos() {

    }

    public void setEsResumen(TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum) {
        required(tipoComprobanteTributarioEnum, "TipoComprobanteTributarioEnum es requerido para determinar si es resumen");
        if (tipoComprobanteTributarioEnum == TipoComprobanteTributarioEnum.FACTURA_CONSUMO) {
            BigDecimal total = this.encabezado.getTotalesEncabezado().getMontoTotal();
            this.esResumen = total.compareTo(UMBRAL_FACTURA_CONSUMO_RESUMIDA) <= 0;
        }
    }

    /**
     * Asigna la firma y genera el código de seguridad automáticamente.
     * Siempre debe llamarse antes de generar el XML resumido o validar contra XSD.
     */
    public void asignarFirmaExtendida(String hashFirma) {
        if (hashFirma == null || hashFirma.length() < 6) {
            throw new IllegalArgumentException("El hash de la firma es inválido o demasiado corto");
        }

        this.hashFirmaComprobanteExtendido = hashFirma;
        this.codigoSeguridad = hashFirma.substring(0, 6);
    }


}

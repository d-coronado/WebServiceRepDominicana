package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Encabezado.Encabezado;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Model.Item.Item;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteBaseValidator;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteEspecificoValidator;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.ComprobanteValidatorFactory;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.AmbienteEnum;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.ComprobanteConstantes.UMBRAL_FACTURA_CONSUMO_RESUMIDA;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.ComprobanteConstantes.VERSION_FORMATO;
import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.FuncionesGenericas.rellenarConCeros;


@Getter
@Setter
@Builder
public class Comprobante {

    private final AmbienteEnum ambienteEnum;
    private final TipoComprobanteTributarioEnum tipoComprobanteTributarioEnum;
    private final String fechaEmision;
    private final Encabezado encabezado;
    private final List<Item> items;
    private final List<SubTotal> subtotales;
    private final List<DescuentoORecargo> descuentosORecargos;
    private final List<PaginaSubTotal> paginas;
    private final InformacionReferencia informacionReferencia;
    private String fechaHoraFirma;
    private String encf;
    private String hashFirma;
    private String codigoSeguridad;
    private boolean esResumen = false;

    public void validarGenerico() {
        ComprobanteBaseValidator.execute(this);
    }

    public void validarSegunTipoComprobante() {
        // Devuelve una instacia segun el tipo de comprobante y hace sus validaciones especificas.
        ComprobanteEspecificoValidator comprobanteEspecificoValidator = ComprobanteValidatorFactory.getValidator(this.tipoComprobanteTributarioEnum);
        comprobanteEspecificoValidator.execute(this);
    }

    public void enriquecer() {
        this.fechaHoraFirma = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.encabezado.setVersion(VERSION_FORMATO);
        construirEncf();
        redondearCalculos();
    }

    private void construirEncf() {
        final String tipoComprobanteValor = this.tipoComprobanteTributarioEnum.getValor().toString();
        final String secuencia = this.encabezado.getDocEncabezado().getSecuencia();
        final String secuenciaCompleta = rellenarConCeros(secuencia, 10);
        this.encf = "E".concat(tipoComprobanteValor).concat(secuenciaCompleta);
    }

    private void redondearCalculos() {

    }

    public void setEsResumen() {
        if (this.tipoComprobanteTributarioEnum == TipoComprobanteTributarioEnum.FACTURA_CONSUMO) {
            BigDecimal total = this.encabezado.getTotalesEncabezado().getMontoTotal();
            this.esResumen = total.compareTo(UMBRAL_FACTURA_CONSUMO_RESUMIDA) <= 0;
        }
    }


}

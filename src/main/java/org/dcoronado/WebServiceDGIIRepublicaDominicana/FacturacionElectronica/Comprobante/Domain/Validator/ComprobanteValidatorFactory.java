package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator.TiposComprobantes.*;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.TipoComprobanteTributarioEnum;

import java.util.Map;

public final class ComprobanteValidatorFactory {

    private ComprobanteValidatorFactory() {}

    private static final Map<TipoComprobanteTributarioEnum, ComprobanteEspecificoValidator> validators = Map.of(
            TipoComprobanteTributarioEnum.FACTURA_CREDITO_FISCAL, new FacturaCreditoFiscalValidator(),
            TipoComprobanteTributarioEnum.FACTURA_CONSUMO, new FacturaConsumoValidator(),
            TipoComprobanteTributarioEnum.NOTA_DEBITO, new NotaDebitoValidator(),
            TipoComprobanteTributarioEnum.NOTA_CREDITO, new NotaCreditoValidator(),
            TipoComprobanteTributarioEnum.COMPRAS, new ComprasValidator(),
            TipoComprobanteTributarioEnum.GASTOS_MENORES, new GastosMenoresValidator(),
            TipoComprobanteTributarioEnum.REGIMENES_ESPECIALES, new RegimenesEspecialesValidator(),
            TipoComprobanteTributarioEnum.GUBERNAMENTAL, new GubernamentalValidator(),
            TipoComprobanteTributarioEnum.COMPROBANTE_EXPORTACION, new ComprobanteExportacionValidator(),
            TipoComprobanteTributarioEnum.COMPROBANTE_PAGO_EXTERIOR, new ComprobantePagoExteriorValidator()
    );

    public static ComprobanteEspecificoValidator getValidator(TipoComprobanteTributarioEnum tipo) {
        ComprobanteEspecificoValidator validator = validators.get(tipo);
        if (validator == null) throw new InvalidArgumentException("No existe validator para tipo: " + tipo);
        return validator;
    }
}

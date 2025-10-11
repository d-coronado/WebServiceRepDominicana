package org.dcoronado.WebServiceDGIIRepublicaDominicana.FacturacionElectronica.Comprobante.Domain.Validator;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Execption.InvalidArgumentException;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.TipoComprobanteTributarioEnum;

import java.util.Map;

public class ComprobanteValidatorFactory {

    private static final Map<TipoComprobanteTributarioEnum, ComprobanteValidator> validators = Map.of(
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

    public static ComprobanteValidator getValidator(TipoComprobanteTributarioEnum tipo) {
        ComprobanteValidator validator = validators.get(tipo);
        if (validator == null) throw new InvalidArgumentException("No existe validator para tipo: " + tipo);
        return validator;
    }
}

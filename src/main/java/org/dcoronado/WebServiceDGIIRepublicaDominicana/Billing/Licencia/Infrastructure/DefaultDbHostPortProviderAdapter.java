package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.DatabaseHostPortProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain.Model.Licencia;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultDbHostPortProviderAdapter implements DatabaseHostPortProviderPort {

    private final DatabaseProperties databaseProperties;

    @Override
    public void apply(Licencia licencia) {
        licencia.setUrlConexionBD(databaseProperties.getHost(), databaseProperties.getPort());
    }
}

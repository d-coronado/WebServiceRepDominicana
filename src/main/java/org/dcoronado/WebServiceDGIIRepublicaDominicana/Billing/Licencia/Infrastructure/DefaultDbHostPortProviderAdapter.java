package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Infrastructure;

import lombok.RequiredArgsConstructor;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.DatabaseHostPortProviderPort;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out.Dto.DatabaseHostInfo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultDbHostPortProviderAdapter implements DatabaseHostPortProviderPort {

    private final DatabaseProperties databaseProperties;

    @Override
    public DatabaseHostInfo provide() {
        return new DatabaseHostInfo(databaseProperties.getHost(), databaseProperties.getPort());
    }
}

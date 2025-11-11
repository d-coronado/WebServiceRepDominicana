package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.TreeNodeDto;

public interface SetupDirectoriesPort {
    void createDirectory(TreeNodeDto estructura);
}

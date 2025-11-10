package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject.TreeNode;

public interface SetupDirectoriesPort {
    void createDirectory(TreeNode estructura);
}

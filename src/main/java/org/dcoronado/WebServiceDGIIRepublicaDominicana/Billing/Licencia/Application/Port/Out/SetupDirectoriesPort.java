package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Application.Port.Out;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Model.TreeNode;

public interface SetupDirectoriesPort {
    void createDirectory(TreeNode estructura);
}

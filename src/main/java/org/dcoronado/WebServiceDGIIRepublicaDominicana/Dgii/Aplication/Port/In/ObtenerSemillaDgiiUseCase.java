package org.dcoronado.WebServiceDGIIRepublicaDominicana.Dgii.Aplication.Port.In;

import org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Enum.AmbienteEnum;

public interface ObtenerSemillaDgiiUseCase {
    String obtenerSemilla(AmbienteEnum ambiente);
}

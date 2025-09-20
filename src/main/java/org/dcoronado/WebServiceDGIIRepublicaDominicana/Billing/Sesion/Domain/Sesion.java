package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Sesion.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dcoronado.WebServiceDGIIRepublicaDominicana.Util.Enum.Ambiente;

import java.time.LocalDateTime;

import static org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.Assert.notBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sesion {
    private String rnc;
    private Ambiente ambiente;
    private LocalDateTime expedido;
    private LocalDateTime expira;
    private String token;

    public void validar() {
        notBlank(this.rnc, "RNC required");
        notBlank(this.ambiente.getCodigo(), "Ambiente required");
    }

}

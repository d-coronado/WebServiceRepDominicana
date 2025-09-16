package org.dcoronado.WebServiceDGIIRepublicaDominicana.Billing.Licencia.Domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DirectorioNode {

    private final String nombre;
    private final List<DirectorioNode> hijos = new ArrayList<>();

    public DirectorioNode(String nombre) {
        this.nombre = nombre;
    }

    public void agregarHijo(DirectorioNode hijo) {
        this.hijos.add(hijo);
    }

}

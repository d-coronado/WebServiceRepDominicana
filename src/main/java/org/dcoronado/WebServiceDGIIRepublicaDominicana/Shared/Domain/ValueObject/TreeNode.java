package org.dcoronado.WebServiceDGIIRepublicaDominicana.Shared.Domain.ValueObject;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TreeNode {

    private final String nombre;
    private final List<TreeNode> hijos = new ArrayList<>();

    public TreeNode(String nombre) {
        this.nombre = nombre;
    }

    public void agregarHijo(TreeNode hijo) {
        this.hijos.add(hijo);
    }

}

package com.example.a20recycleview;

import java.util.ArrayList;

public class PaisRepositorio {

    public static ArrayList<String> obtenerPaises() {
        ArrayList<String> paises = new ArrayList<>();
        paises.add("China");
        paises.add("France");
        paises.add("Germany");
        paises.add("India");
        paises.add("Rusia");
        paises.add("United Kingdom");
        paises.add("United States");
        return paises;
    }
}
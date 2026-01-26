package com.example.a23listviewcomplejo;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private String ciudad;
    private String pais;
    private int fundacion;
    private int idImage;

    public Equipo(String nombre, String ciudad, String pais, int fundacion, int idImage) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fundacion = fundacion;
        this.idImage = idImage;
    }

    //sin setters porque no se van a modificar los datos
    //getters para obtener los datos

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public int getFundacion() {
        return fundacion;
    }

    public int getIdImage() {
        return idImage;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", fundacion=" + fundacion +
                ", idImage=" + idImage +
                '}';
    }




}

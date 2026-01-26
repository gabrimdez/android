package com.example.a48misdiscos;

public class Disco {

    private int id;
    private String nombre;
    private String artista;
    private String genero;
    private int duracion;
    private int anio;

    public Disco(int id, String nombre, String artista, String genero, int duracion, int anio) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.genero = genero;
        this.duracion = duracion;
        this.anio = anio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getAnio() {
        return anio;
    }
}

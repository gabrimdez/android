package com.example.a21rviewcomplejo;

public class ItemData {
    private String textoSuperior;
    private String textoInferior;
    private int idImagen;  // recurso drawable asociado

    public ItemData(String textoSuperior, String textoInferior, int idImagen) {
        this.textoSuperior = textoSuperior;
        this.textoInferior = textoInferior;
        this.idImagen = idImagen;
    }

    public String getTextoSuperior() {
        return textoSuperior;
    }

    public void setTextoSuperior(String textoSuperior) {
        this.textoSuperior = textoSuperior;
    }

    public String getTextoInferior() {
        return textoInferior;
    }

    public void setTextoInferior(String textoInferior) {
        this.textoInferior = textoInferior;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }


}
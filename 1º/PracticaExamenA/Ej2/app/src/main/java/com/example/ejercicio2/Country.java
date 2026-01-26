package com.example.ejercicio2;

public class Country {
    private String name;
    private String capital;
    private String population;
    private int flagRes;

    public Country(String name, String capital, String population, int flagRes) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.flagRes = flagRes;
    }

    public String getName() { return name; }
    public String getCapital() { return capital; }
    public String getPopulation() { return population; }
    public int getFlagRes() { return flagRes; }
}

package com.example.a24spinnercomplejo;

import androidx.annotation.NonNull;

public class Team {

    private String name;
    private String city;
    private String country;
    private int years;
    private int crest;

    public Team(String name, String city, String country, int years, int crest) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.years = years;
        this.crest = crest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getCrest() {
        return crest;
    }

    public void setCrest(int crest) {
        this.crest = crest;
    }


    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", years=" + years +
                ", crest=" + crest +
                '}';
    }
}

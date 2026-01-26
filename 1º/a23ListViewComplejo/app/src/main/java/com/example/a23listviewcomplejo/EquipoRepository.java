package com.example.a23listviewcomplejo;

import java.util.ArrayList;

public class EquipoRepository {

    ArrayList<Equipo> lista;

    public EquipoRepository() {
        lista = poblarSpinner();
    }

    public ArrayList<Equipo> getLista() {
        return lista;
    }

    public static ArrayList<Equipo> poblarSpinner() {
        ArrayList<Equipo> lista = new ArrayList<>();
        Equipo equipo1 = new Equipo("Maccabi Basketball Club",
                "Tel Aviv", "Israel", 1932, R.drawable.club2);
        lista.add(equipo1);
        Equipo equipo2 = new Equipo("Košarkaški Klub Crvena Zvezda",
                "Belgrado", "Serbia", 1945, R.drawable.club1);
        lista.add(equipo2);
        Equipo equipo3 = new Equipo("Basketball Club Žalgiris",
                "Kaunas", "Lituania", 1944, R.drawable.club3);
        lista.add(equipo3);
        Equipo equipo4 = new Equipo("Club Deportivo Baskonia "
                , "Vitoria", " España", 1959, R.drawable.club4);
        lista.add(equipo4);
        Equipo equipo5 = new Equipo("Panathinaikos Basketball Club"
                , "Atenas", "Grecia", 1919, R.drawable.club5);
        lista.add(equipo5);
        return lista;
    }

}

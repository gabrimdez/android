package com.example.a24spinnercomplejo;

import java.util.ArrayList;

public class TeamRepository {

    private static ArrayList<Team> teams;

    public TeamRepository() {
        populateTeams();
    }

    private static ArrayList<Team> populateTeams() {
        teams = new ArrayList<Team>();
        teams.add(new Team("Racing Club", "Avellaneda", "Argentina", 1903, R.drawable.image6));
        teams.add(new Team("SE Palmeiras", "Sao Paulo", "Brasil", 1914, R.drawable.image8));
        teams.add(new Team("Club Nacional", "Montevideo", "Uruguay", 1899, R.drawable.image7));
        teams.add(new Team("Atlético Nacional", "Medellin", "Colombia", 1947, R.drawable.image10));
        teams.add(new Team("Club Olimpia", "Asuncion", "Paraguay", 1902, R.drawable.image9));
        return teams;
    }

    public static ArrayList<Team> getTeams() {
        return TeamRepository.populateTeams();
    }


}

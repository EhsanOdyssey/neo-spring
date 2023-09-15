package neo.ehsanodyssey.java.spring.entities;

public interface Game {
    void setHomeTeam(Team homeTeam);
    Team getHomeTeam();
    void setAwayTeam(Team awayTeam);
    Team getAwayTeam();
    String playGame();
}

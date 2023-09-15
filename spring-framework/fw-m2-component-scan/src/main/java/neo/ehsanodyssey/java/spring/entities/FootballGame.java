package neo.ehsanodyssey.java.spring.entities;

import javax.sql.DataSource;

public class FootballGame implements Game {
    private Team homeTeam;
    private Team awayTeam;
    private DataSource dataSource;

    public FootballGame() {
    }

    public FootballGame(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Override
    public Team getHomeTeam() {
        return this.homeTeam;
    }

    @Override
    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public Team getAwayTeam() {
        return this.awayTeam;
    }

    @Override
    public String playGame() {
        return Math.random() < 0.5 ? getHomeTeam().getName() : getAwayTeam().getName();
    }

    @Override
    public String toString() {
        return String.format("Game between %s at %s", awayTeam.getName(), homeTeam.getName());
    }
}

package neo.ehsanodyssey.java.spring;

import neo.ehsanodyssey.java.spring.entities.*;

public class RunWithoutSpring {

    public static void main(String[] args) {
        Team persepolis = new Persepolis();
        Team esteghlal = new Esteghlal();
        Game game = new FootballGame(persepolis, esteghlal);
        System.out.println(game.playGame());
    }
}

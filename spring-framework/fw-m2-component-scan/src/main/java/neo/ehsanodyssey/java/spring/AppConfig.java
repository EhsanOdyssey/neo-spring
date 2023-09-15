package neo.ehsanodyssey.java.spring;

import neo.ehsanodyssey.java.spring.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.text.NumberFormat;

@Configuration
@ComponentScan(basePackages = "neo.ehsanodyssey.java.spring")
public class AppConfig {
    @Autowired
    private DataSource dataSource;

    // Case 1: Use Java standard @Resource annotation
    // @Resource means:
    //      Java standard annotation
    //      This uses "autowire by name"
    @Resource
    private Team persepolis;

    @Resource
    private Team esteghlal;

    @Bean
    public Game gameByResource() {
        FootballGame footballGame = new FootballGame(persepolis, esteghlal);
        footballGame.setDataSource(dataSource);
        return footballGame;
    }

    // Case 2: Use Spring @Autowired annotation
    // @Autowired means:
    //      "autowire by type" first
    //      This works if there is exactly one bean of that type (class) available
    @Autowired
    @Qualifier("persepolis")
    private Team homeTeam;

    @Autowired
    @Qualifier("esteghlal")
    private Team awayTeam;

    @Bean
    public Game gameByAutowire() {
        FootballGame footballGame = new FootballGame(homeTeam, awayTeam);
        footballGame.setDataSource(dataSource);
        return footballGame;
    }

    // Use Abstract classes as a Bean
    // This type beans are FactoryBean because used FacoryMethod of a Abstract class for instantiating the type
    @Bean
    public NumberFormat nf() {
        return NumberFormat.getCurrencyInstance();
    }
}

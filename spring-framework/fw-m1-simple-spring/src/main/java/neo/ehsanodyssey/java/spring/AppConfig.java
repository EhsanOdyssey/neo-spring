package neo.ehsanodyssey.java.spring;

import neo.ehsanodyssey.java.spring.entities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

// @Configuration annotation indicate that this file is not part of business logic or entity or service
// it is in fact there to communicate with to spring that what to do. This is a metadata in java class form
@Configuration
// import another configuration in main configuration class for separating configurations
@Import(InfrastructureConfig.class)
public class AppConfig {

    // We can use initMethod and destroyMethod of @Bean or @PostConstruct & @PreDestroy (standard j2ee annotations)
    // in the object (here is FootballGame) to do anything in PostConstruct and PreDestroy phases
    // Please note that we should use AnnotationConfigApplicationContext that has close() method for executing
    // destroyMethod of @Bean or @PreDestroy method in object
    @Bean(initMethod = "startGame", destroyMethod = "endGame")
    public Game game(DataSource dataSource) {
        FootballGame footballGame = new FootballGame(persepolis(), esteghlal());
        footballGame.setDataSource(dataSource);
        return footballGame;
    }

//    @Bean
//    public Game game(DataSource dataSource) {
//        FootballGame footballGame = new FootballGame(persepolis(), esteghlal());
//        footballGame.setDataSource(dataSource);
//        return footballGame;
//    }

    @Bean
    public Team persepolis() {
        return new Persepolis();
    }

    @Bean
    public Team esteghlal() {
        return new Esteghlal();
    }
}

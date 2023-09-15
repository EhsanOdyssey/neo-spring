package neo.ehsanodyssey.java.spring;

import neo.ehsanodyssey.java.spring.entities.Game;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunWithSpring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Game game = context.getBean("game", Game.class);
        for (String name: context.getBeanDefinitionNames()) {
            System.out.println("Bean: " + name);
        }
        System.out.println("Game Winner: " + game.playGame());
        context.close();
    }
}

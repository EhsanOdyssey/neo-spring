package neo.ehsanodyssey.java.spring;

import neo.ehsanodyssey.java.spring.entities.Game;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.NumberFormat;

public class RunWithSpring {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Test Case 1
        System.out.println("Test Case 1");
        Game gameByResource = context.getBean("gameByResource", Game.class);
        for (String name: context.getBeanDefinitionNames()) {
            System.out.println("Bean: " + name);
        }
        System.out.println("Game over: " + gameByResource.playGame());
        // Test Case 2
        System.out.println("Test Case 2");
        Game game = context.getBean("gameByAutowire", Game.class);
        for (String name: context.getBeanDefinitionNames()) {
            System.out.println("Bean: " + name);
        }
        System.out.println("Game over: " + game.playGame());
        // Use Abstract classes as a Bean
        NumberFormat numberFormat = context.getBean(NumberFormat.class);
        double amount = 15800.76564;
        System.out.println("Formatted Amount: " + numberFormat.format(amount));
    }
}

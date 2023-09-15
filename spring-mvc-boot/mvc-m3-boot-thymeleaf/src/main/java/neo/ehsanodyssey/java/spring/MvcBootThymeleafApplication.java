package neo.ehsanodyssey.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// SpringBootApplication is contained the annotations for registering IoC container by (@SpringBootConfiguration)
// and configuring other beans based on annotations that user will be used in other classes like @Service, @Controller, ... by (@EnableAutoConfiguration)
@SpringBootApplication // Performs component scanning
public class MvcBootThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcBootThymeleafApplication.class, args);
    }
}

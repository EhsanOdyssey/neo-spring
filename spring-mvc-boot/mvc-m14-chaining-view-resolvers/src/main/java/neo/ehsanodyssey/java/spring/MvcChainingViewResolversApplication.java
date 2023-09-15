package neo.ehsanodyssey.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// SpringBootApplication is contained the annotations for registering IoC container by (@SpringBootConfiguration)
// and configuring other beans based on annotations that user will be used in other classes like @Service, @Controller, ... by (@EnableAutoConfiguration)
@SpringBootApplication // Performs component scanning
public class MvcChainingViewResolversApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MvcChainingViewResolversApplication.class, args);
    }
}

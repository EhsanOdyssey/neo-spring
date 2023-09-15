package neo.ehsanodyssey.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
// Enabling Web Security config with default configuration
// By default for first time login page will appear for user to login to the system
@EnableWebSecurity
public class MvcTestingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MvcTestingApplication.class, args);
    }
}

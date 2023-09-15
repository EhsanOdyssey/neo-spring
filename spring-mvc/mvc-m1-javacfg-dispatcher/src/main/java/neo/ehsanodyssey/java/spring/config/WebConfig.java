package neo.ehsanodyssey.java.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan("neo.ehsanodyssey.java.spring.controller")
public class WebConfig {
}

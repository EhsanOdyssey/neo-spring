package neo.ehsanodyssey.java.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan("neo.ehsanodyssey.java.spring.controller")
public class WebConfig {

    // Register InternalResourceViewResolver bean with the dispatcher servlet application context
    @Bean
    public InternalResourceViewResolver viewResolver() {
        // below object is used to access files within the WEB-INF directory
        // it is important because, content within the WEB-INF directory is not directly accessible
        // I cannot construct the URL in the browser in that reaches into the WEB-INF directory impose a jsp file
        // so InternalResourceViewResolver can expose that content for dispatcher servlet when it is boundaries the view
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

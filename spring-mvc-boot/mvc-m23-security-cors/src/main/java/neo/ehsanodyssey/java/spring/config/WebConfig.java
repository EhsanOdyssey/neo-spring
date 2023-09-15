package neo.ehsanodyssey.java.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Global CORS (Cross Origin Resource Sharing) Configuration
    // We can send request with header (Origin="http://somedomain.com") with another Origin headers we will get error 403-Forbidden exception
    // We can specify allowed methods that user can send by request by allowedMethods. In bellow we allowed all methods
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://somedomain.com").allowedMethods("*");
    }
}

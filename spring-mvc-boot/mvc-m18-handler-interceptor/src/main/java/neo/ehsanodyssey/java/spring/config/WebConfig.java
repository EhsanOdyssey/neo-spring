package neo.ehsanodyssey.java.spring.config;

import neo.ehsanodyssey.java.spring.interceptors.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // We can add interceptor path if we want to applying interceptor operation to specific paths
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/project/**");
    }
}

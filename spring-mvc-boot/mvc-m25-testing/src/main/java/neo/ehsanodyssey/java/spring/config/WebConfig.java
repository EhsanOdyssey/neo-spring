package neo.ehsanodyssey.java.spring.config;

import neo.ehsanodyssey.java.spring.converters.JulianDateConverter;
import neo.ehsanodyssey.java.spring.interceptors.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://somedomain.com").allowedMethods("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new JulianDateConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // We can add interceptor path if we want to applying interceptor operation to specific paths
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/project/**");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .favorParameter(true)
                .parameterName("accept")
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }
}

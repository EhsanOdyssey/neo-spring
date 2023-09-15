package neo.ehsanodyssey.java.spring.config;

import neo.ehsanodyssey.java.spring.converters.JulianDateConverter;
import neo.ehsanodyssey.java.spring.converters.ResourceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ResourceConverter resourceConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new JulianDateConverter());
        registry.addConverter(resourceConverter);
    }
}

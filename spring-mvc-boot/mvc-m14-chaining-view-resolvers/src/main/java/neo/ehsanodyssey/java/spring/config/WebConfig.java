package neo.ehsanodyssey.java.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@Configuration
public class WebConfig {

    @Bean
    public ViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("views");
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Bean
    public ViewResolver xmlViewResolver() {
        final XmlViewResolver bean = new XmlViewResolver();
        bean.setLocation(new ClassPathResource("views.xml"));
        bean.setOrder(1);
        return bean;
    }

    // We can specify InternalResourceViewResolver in below or use application.properties for this one configuration
//    @Bean
//    public ViewResolver viewResolver() {
//        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
//        bean.setViewClass(JstlView.class);
//        bean.setPrefix("/WEB-INF/views/");
//        bean.setSuffix(".jsp");
//        bean.setOrder(2);
//        return bean;
//    }
}

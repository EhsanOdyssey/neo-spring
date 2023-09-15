package neo.ehsanodyssey.java.spring;

import neo.ehsanodyssey.java.spring.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // bootstrap IOC
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // register configuration class
        context.register(WebConfig.class);
        // define dispatcher servlet
        DispatcherServlet dispatcher = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcher);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}

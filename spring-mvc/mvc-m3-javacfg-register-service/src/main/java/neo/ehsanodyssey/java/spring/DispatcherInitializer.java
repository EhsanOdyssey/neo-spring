package neo.ehsanodyssey.java.spring;

import neo.ehsanodyssey.java.spring.config.RootConfig;
import neo.ehsanodyssey.java.spring.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Service instances support in application should be separated from the objects that support to the application web tier

    // this method is used to establish root application context for global beans and services that may be shared in across the application in multiple dispatcher servlets
    // the application context as holding the non web infrastructure
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    // all of the web infrastructure will be contained in the application context with the associated dispatcher servlet
    // the dispatcher servlet application context contains beans specific to that particular dispatcher
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}

package neo.ehsanodyssey.java.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {
    // The BeanFactory Interface:
    //      This is the root interface for accessing the Spring container. To access the Spring container, we will be
    //      using Spring's dependency injection functionality using the BeanFactory interface and its sub-interfaces.
    //      Usually, the implementations use [lazy loading], which means that beans are only instantiating when we
    //      directly calling them through the getBean() method.
    //      Features:
    //          Bean instantiation/wiring
    // The ApplicationContext Interface:
    //      The ApplicationContext is the central interface within a Spring application that is used for providing
    //      configuration information to the application. It implements the BeanFactory interface. Hence,
    //      the ApplicationContext includes all functionality of the BeanFactory and much more! Its main function is
    //      to support the creation of big business applications. It uses [eager loading], so every bean instantiate
    //      after the ApplicationContext is started up.
    //      Features:
    //          Bean instantiation/wiring
    //          Automatic BeanPostProcessor registration
    //          Automatic BeanFactoryPostProcessor registration
    //          Convenient MessageSource access (for i18n)
    //          ApplicationEvent publication

    // If the ApplicationContext is the heart, the BeanFactory is the ventricles of that heart.
    // The ApplicationContext is a read-only wrapper of the BeanFactory, and all of your runtime interactions
    // with that BeanFactory or any beans contained in that factory is through this ApplicationContext.
    // The ApplicationContext provides the metadata for all beans created, and also provides a mechanism
    // for creating beans in the correct order. The ApplicationContext is the Inversion of Control container (IoC)
    // and all of your Dependency Injections occur here.
    // In fact, much of Spring is configuring the context to control the Dependency Injections.
    // The ApplicationContext manages all of our singleton beans in the application itself. It also serves all
    // prototype or session scoped beans.

    // In below, we will initially interact directly with an implementation of the ApplicationContext, but most normal
    // use cases have no need to directly interact with the context itself.
    // That being said, any class can be made aware of the ApplicationContext through appropriate Spring interfaces.

    @Bean
    public String greeting() {
        return "Hello World...";
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloConfig.class);
        String text = (String) context.getBean("greeting");
        System.out.println(text);
    }
}

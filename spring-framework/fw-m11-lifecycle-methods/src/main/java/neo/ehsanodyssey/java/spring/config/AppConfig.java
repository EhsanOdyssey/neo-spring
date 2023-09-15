package neo.ehsanodyssey.java.spring.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class AppConfig {
    // There are oftentimes when writing code that we need to execute some functionality right after a class is
    // constructed or right before it is destroyed. Though these are not the most common needs, they do exist.
    // In my experience, moving to cloud native applications increases the need to execute cache warmers and other
    // performance-related options after startup because you need the application itself to start quickly.
    // Spring provides facilities to support these behaviors for these types of operations when using
    // Java configuration or auto configuration. These annotations are actually Java annotations so not part of
    // Spring per se, but we will include them nonetheless.
    // During the application initialization phase, you cannot leverage Spring or its proxies to do work. You cannot
    // for instance in a constructor call a proxy data repository or leverage a proxy cache provider. You also cannot
    // leverage Spring after the application goes out of scope but before garbage collection, the so called destructor.
    // PostConstruct:
    //      The @PostConstruct annotation allows you to specify a single method on a class that is executed
    //      after construction. This, however, is the first opportunity, as I previously mentioned, that you can
    //      leverage Spring proxies and Spring managed beans to do work in your application and that is critical
    //      for those use cases where this becomes relevant. To leverage the PostConstruct annotation, you create
    //      a void method and you annotate it.
    //      Spring will execute your method after Spring is ready so that you can use the full functionality of
    //      your dependencies and their proxies. Post construction methods may be more apparent, but often in
    //      the same use cases of cloud native architectures where a PostConstruct method is needed, a situation
    //      exists to clean up prior to losing scope.
    //      * @PostConstruct operates the same as the InitializingBean interface.
    //      * After the properties are set on all beans, the method is called.
    // PreDestroy:
    //      All too often, we have to respond to an unpredictable starting or stopping of our application instances
    //      and part of that is controlling shutdown and saving our data. For these instances, Spring leverages
    //      a @PreDestroy annotation. It works much the same way that a PostConstruct annotation works.
    //      However, there is a caveat here. Beans that are scoped as prototype for instance are not impacted
    //      because the Spring bean factory doesn't have a handle on these beans after they are constructed
    //      and the facility to call these methods is called as the ApplicationContext closes.
    //      * @PreDestroy operates the same as the DisposableBean interface.
    //      * Executed when ApplicationContext closes.

    @PostConstruct
    public void init() {
        // do some initialization works...
    }

    @PreDestroy
    public void destroy() {
        // do some closing works...
    }
}


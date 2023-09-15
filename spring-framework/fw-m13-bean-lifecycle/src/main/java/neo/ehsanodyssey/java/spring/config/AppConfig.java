package neo.ehsanodyssey.java.spring.config;

public class AppConfig {
    // Phases of a Spring lifecycle.
    // Initialization Phase:
    //      Most of the work that we do within the Spring framework, through the configuration of the application
    //      context directly impacts the initialization phase. This is where we're going to spend most of our time
    //      as we dissect each and every point within the initialization phase itself.
    //      A) Begins with the creation of the ApplicationContext:
    //          The initialization phase of a Spring Bean application's lifecycle begins with the creation of the
    //          ApplicationContext. And indeed that is of valid use case when dealing with batch operations and
    //          similar functions. But all too often the ApplicationContext is actually not manually created at all.
    //          When you're running a web application the ApplicationContext is actually started as part of
    //          the Servlet initialization phase itself. The Servlet calls in and starts the ApplicationContext
    //          and indeed the ServletContext is what actually has the handle to the ApplicationContext.
    //      B) BeanFactory initialization phase:
    //          The first part of the initialization phase, after the ApplicationContext starts is the BeanFactory
    //          initialization phase. And there's really a key point of integration here in the BeanFactory that
    //          we're going to talk about. But know that the ApplicationContext, again, is the wrapper for
    //          the BeanFactory, but the BeanFactory itself has to be brought up correctly. And that is done during
    //          the BeanFactory initialization phase of the Spring Bean lifecycle.
    //      C) Bean initialization and instantiation:
    //          After the BeanFactory is primed and ready to run we'll get into individual bean initialization and
    //          instantiation operations. There's all kinds of places here that we can impact the behavior of
    //          an individual bean and during the initialization phase most if not all of those are set. So let's
    //          look at high level overview of what typically happens during the initialization phase of a Spring
    //          application.
    //      High level overview of Initialization Phase:
    //          A) The BeanFactory is loaded: It occurs in two distinct points.
    //              1) Bean definitions loaded:
    //                      The bean definitions themselves are loaded into the BeanFactory through their metadata.
    //                      There are several sources of bean definition (Getting our metadata into the bean factory):
    //                          a) Java configuration
    //                          b) XML configuration
    //                          c) Component scanning & Auto configuration
    //                      All the beans have been loaded into the bean factory from all sources and remember we can
    //                      mix and match our sources. And Id is used to create the index of each bean in
    //                      the bean factory. Now an important thing to note here is that the bean factory at this
    //                      point only contains references, no classes have been created no beans have been defined
    //                      and no proxies have been wrapped around them. We only have references to beans in our
    //                      bean factory at this point of the life cycle.
    //              2) Post-process Bean definitions:
    //                      There's a process by which we actually do post-processing on the bean definitions
    //                      themselves, called BeanFactory post-processing. After all of the beans have been loaded
    //                      into the BeanFactory, Spring takes a moment to process all of those beans.
    //                          a) BeanFactory Post-Processors perform their work on the entire BeanFactory.
    //                          b) They can modify or transform any bean in the factory, prior to its initialization
    //                              or instantiation.
    //                          c) Most familiar example of a BeanFactory Post-Processor, is the
    //                              PropertySourcesPlaceholderConfigurer. It takes property files, parses them, and
    //                              injects the property values into the bean before it's ever instantiated, and that
    //                              is the typical behavior of what a BeanFactory Post-Processor will do.
    //                              It will modify any and all beans that meet the criteria for its operation, but it
    //                              does so on a global level at the BeanFactory itself. You can create your own
    //                              BeanFactory Post-Processor by extending the interface.
    //                          d) BeanFactoryPostProcessor interface: It is really the first extension point in
    //                              the lifecycle itself.
    //                              #) You can write custom components to impact the BeanFactory as a whole. While
    //                                  this isn't a very common operation
    //                              #) You often will use existing BeanFactory Post-Processors in your configuration.
    //                                  Such as the one used for registering Scope, or the aforementioned properties
    //                                  use case.
    //                              #) If you do choose to write your own BeanFactory Post-Processor, and you're
    //                                  using Java Config, your bean definition itself must be a [static method].
    //                                  The reason for this is, this removes the side effects that could occur of
    //                                  having a dynamic instance of that method. By making it class level, by making
    //                                  it static, we remove those side effects.
    //          B) Iterate through all of the beans for processing: Once the BeanFactory itself is initialized and
    //            ready to be used, then we iterate through all of the beans in that factory and go through a process
    //            that occurs on each individual bean and they are all done in this specific order. For each bean
    //              1) Instantiate Bean:
    //                      Each bean is instantiated in the factory using their constructors. Now they are done in
    //                      the correct order to ensure the dependencies are created first so that we're not injecting
    //                      null values into beans that are bean created through this instantiation phase. The handle
    //                      to each class instance remains in the bean factory for the lifecycle of the application
    //                      for singleton beans. Other scope beans have different ways that they are managed, but for
    //                      the most part, a majority of your beans are going to be singletons, so just remember that
    //                      the bean factory itself maintains the handle. There are two ways that beans themselves
    //                      can be instantiated, eager or lazy. By default, all beans are instantiated eagerly,
    //                      meaning that as the bean factory itself is processed, every bean within that factory
    //                      is instantiated. In order for a bean to be lazily instantiated, it not only needs to be
    //                      annotated as lazy, but it also has to have nothing else in the bean factory that uses
    //                      them as a dependency. If a bean is a dependency of another bean, it cannot be lazily
    //                      instantiated. So when you specify that a bean is lazy, the ApplicationContext, and
    //                      specifically the bean factory, will honor that. However, because of the way it manages
    //                      the dependency graph, it always reserves the right to ignore your annotation.
    //                      When this phase is completed, there is a pointer to each bean in the BeanFactory.
    //                      The objects themselves have been constructed, but, and an important point to note here,
    //                      they are not available for use yet, and we have to regress through the rest of the
    //                      lifecycle in order for them to be available.
    //              2) Setters called:
    //                      Once all of the beans have been instantiated and all of the required dependencies injected
    //                      with those constructor-level injections, we now have fully qualified classes ready to
    //                      be used. However, Spring then goes through each of those classes and modifies them through
    //                      the setter argument. setter injection doesn't just include those methods with a
    //                      set annotation. Once all of these beans have been created, all of those methods with
    //                      set property will definitely be called if they are appropriately constructed through
    //                      property injection or autowiring. However, field-based injection also occurs here.
    //                      If you have a field, even though we specifically spelled out the reasons why we shouldn't
    //                      do field-level injection, it is good to know that this also occurs during this phase.
    //                      All autowiring regardless of whether it's a setter or a field occurs here, with
    //                      the exception of constructor-based autowiring.
    //                      When this phase is complete, all of our beans are fully initialized and instantiated. All
    //                      of the dependencies whether they're optional through setter-level injection or field-level
    //                      injection or required through constructor injection have been injected into the beans as
    //                      appropriate. However, once again, our beans are still not ready for use because Spring
    //                      has not yet had the opportunity to add its behaviors to all of our classes.
    //              3) Bean post-processor (pre-init): Bean post-processors are then executed
    //              4) Initializer: The initializer itself occurs
    //              5) Bean post-processor (post-init): Bean post-processors that happen after the initializer.
    //                      Bean post-processing is the final point of configuration manipulation. Each bean may have
    //                      additional behaviors added either by the framework itself or by you the developer through
    //                      extending specific interfaces. There are two types of extensible and generic processing
    //                      that occurs before and after the initializer itself through this bean post-processing
    //                      process. We're going to talk about the initializer first, however and the initializer
    //                      is actually the second point of this entire phase. This is called out because it's a
    //                      special case. When we talked about life cycle annotations previously in the course,
    //                      we talked about @PostConstruct methods and indeed this is the point at which
    //                      @PostConstruct methods are called.
    //                      The framework has many PostConstruct methods itself which inject proxy behavior into
    //                      your beans if they are annotated in specific ways but any class that you have a method
    //                      in which you have annotated with @PostConstruct or used the appropriate XML config will
    //                      have that method called during this phase of bean post-processing.
    //                      There also is a BeanPostProcess Interface and this interface allows you to inject common
    //                      behavior into a specific bean or a class of beans.
    //              6) Ready for Use
    //                      There are two types of methods that you can have before initializer and after initializer,
    //                      also known as pre-init and post-init methods. The framework itself also leverages a lot of
    //                      these BeanPostProcessors. When this phase is complete, you will have beans that are fully
    //                      instantiated and initialized. All of the dependencies will be injected and all behavior
    //                      will be added to each and every bean. At this point, all of our beans are actually ready
    //                      to use and the next logical step is cascading into the use phase itself.
    // Use Phase:
    //      The use phase is actually the single largest point at which your application spends within the lifecycle
    //      itself. For a typical application, over 99% of its time is actually in the use phase of the Spring
    //      lifecycle. We're not going to spend a lot of time talking about it; however, because it really is a very
    //      simple case of IOC. The ApplicationContext serves proxies to the original class during run time of
    //      the application. It also maintains a handle to each bean, if that bean is a singleton, which is
    //      the default behavior if you don't apply a separate scope to your beans.
    //      Now it's important to note here that your ApplicationContext has all of the beans that you have told it
    //      to configure. But you can have more than one ApplicationContext at any given time. And you are only scoped
    //      to the ApplicationContext that contains your class.
    //      An ApplicationContext started by, say the servlet container, can have a handle to the ApplicationContext
    //      that you are using, but the reverse isn't true. The one that you are using does not have a handle to
    //      the parent ApplicationContext. And then after all is good object oriented programming right, you know
    //      about your children, but your children don't know about you. And Spring handles this very eloquently
    //      throughout the use phase. If you do need to have a handle to the ApplicationContext that your bean is
    //      contained within, Spring provides an appropriate interface. The ApplicationContext to where interface
    //      gives your class a handle to the ApplicationContext that manages the bean that your class represents.
    //      That handle is not a very common aspect to have, but it is there if you need it. And all of the interaction
    //      with the ApplicationContext from within your class, if you choose to extend this interface, is used during
    //      the use phase of the application itself.
    // Destruction Phase:
    //      The Destruction phase we spend very little time dealing with throughout the lifecycle of an application.
    //      When an application is in the end of its lifecycle, it goes through a destruction phase.
    //      The destruction phase in a Spring application begins when close is called on the application context.
    //      Now, this can either be a manual call to close or when the framework in which the application itself is
    //      running calls close. As the application context itself is starting to close, any method that is annotated
    //      with @PreDestroy or the corresponding XML notation for a PreDestroy method, that method itself is executed
    //      at that point. An important thing to note is that the beans themselves are not destroyed at this point.
    //      In a Java world, the only thing that can destroy a class itself is the garbage collector so calling close
    //      on the application context makes every bean contained within it go out of scope and allow it to be
    //      garbage collected during the normal processing.
    //      There's a couple caveats when dealing with the destruction phase of a Spring application. One is that
    //      the application context itself cannot be reused again. When close is called, the application context
    //      goes out of scope and all handles to it are released.
    //      Prototype beans are not impacted by the destruction phase either. Because a prototype bean no longer is
    //      handled by the application context once it's constructed, they go out of scope immediately when
    //      the application class itself no longer needs them. And as I previously mentioned, garbage collection is
    //      the only thing that can destroy an instance of a bean. Nothing else that we've talked about in
    //      the destruction phase will make a bean itself be destroyed.
}


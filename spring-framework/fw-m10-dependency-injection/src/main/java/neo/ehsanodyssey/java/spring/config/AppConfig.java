package neo.ehsanodyssey.java.spring.config;

import neo.ehsanodyssey.java.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:/application.properties")
@PropertySource("classpath:/application-${spring.profiles.active}.properties")
@ComponentScan(basePackages = {"neo.ehsanodyssey.java.spring"})
public class AppConfig {
    // Annotation-based configuration or component scanning, is another way of configuring your application context.
    // Indicating beans that need to be managed by the bean factory is as simple as an annotation on the class of
    // [@Component] or one of its stereotypes. Typical stereotypes include [@Controller], [@Service] and [@Repository].
    // Some of these stereotypes add additional behavior to your bean through the proxies that come along with them,
    // such as the @Repository.
    // Dependency injection with component scanning is achieved through auto-wiring [@Autowired annotation].
    // Spring allows you to use the @Qualifier annotation to specify which bean to inject in which case. To complement
    // this, you can provide a bean name on the @Component annotation.
    // This may beg the question of why do component scanning at all? And the answer is simply speed. With
    // component scanning, common configuration can be achieved by simply annotating the class, instead of defining
    // a bean for each class. Scanning occurs during startup and the framework will do all the work for you.
    // In the Spring world, the dependency management is handled for you by the IOC container, but you are responsible
    // for the good practices around encapsulation. The autowiring of beans for dependency management can be a blessing
    // or a curse depending on your focus of good object oriented programming.
    // As I mentioned, the blessing of autowiring comes from the simplicity of dependency management. You don't need
    // to build out any management systems to handle the DI itself. That being said, autowiring can lead to poorly
    // written code.
    // Field-Level Injection:
    //      One way to manage dependencies in code, is field level autowiring. While this may be any easy way to write
    //      your code, it does have many downsides that you need to be aware of. One of the downsides of field level
    //      injection is that you can run into issues writing good JUnit tests. A good unit test leverages mock or
    //      stub dependency, and if you leverage field level injection, you reduce your abilities to inject the mocks
    //      or stubs without frameworks to help.
    //          @Autowired
    //          private final InventoryService inventoryService;
    // Setter-Level Injection:
    //      In addition, good OOP would state, that you're object should be created in a way that you can control
    //      it's construction and with field level injection, you cannot control the construction of your object
    //      because you don't have access to that field through a pubic API. In field-level injection code snippet
    //      I want to reiterate that you shouldn't do this unless your hands really are tied.
    //      This takes us to setter-level injection. Well this indeed solves many of the concerns of dependency
    //      injection for mocks and stubs in testing, it does bring up another important issue with OOP, and that is
    //      protecting your data. By exposing your dependencies through setters, you allow other systems or components
    //      to manipulate the internal data of your class. Now, there are times where you really want to do this and
    //      you need this behavior, as such, you need to allow the opportunity to leverage setter injection, but it
    //      should be reserved for [optional dependencies], or those dependencies that may [change throughout
    //      the lifecycle] of you application. Remember that beans in the Spring world, unless otherwise scoped,
    //      are singletons. So the lifecycle of your bean must be considered when deciding to use setter injection.
    //          private InventoryService inventoryService;
    //
    //          @Autowired
    //          public void setInventoryService(InventoryService inventoryService) {
    //              this.inventoryService = inventoryService;
    //          }
    // Constructor-Level Injection:
    //      A vast majority of Spring bean should be constructed once and then remain immutable throughout
    //      the lifecycle of the application. As such, the preferred method for most situations of autowiring replies
    //      on constructor level injection. Spring will build the beans during it's initialization phase,
    //      in proper order, and with constructor injection your class becomes immutable.
    //      Your attributes should be final since they are set during construction and you get perfect encapsulation,
    //      and immutability of your internal data and dependencies of your class.
    //          private final InventoryService inventoryService;
    //
    //          @Autowired
    //          public OrderServiceImpl(InventoryService inventoryService) {
    //              this.inventoryService = inventoryService;
    //          }

    @Value("${greeting.text}")
    private String greetingText;

    @Value("${greeting.preamble}")
    private String greetingPreamble;

    @Value("#{new Boolean(environment['spring.profiles.active']=='dev')}")
    private boolean isDev;

    public class Worker {
        private String preamble;
        private String text;

        public Worker(String preamble, String text) {
            this.preamble = preamble;
            this.text = text;
			System.out.println("New Instance");
        }

        public void execute() {
            System.out.println(preamble + " " + text + "is dev: " + isDev);
        }
    }

    @Bean
    public Worker worker() {
        return new Worker(greetingPreamble, greetingText);
    }

    public static void main (String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Worker worker = context.getBean(Worker.class);
        worker.execute();
        OrderService orderService = context.getBean(OrderService.class);
    }
}


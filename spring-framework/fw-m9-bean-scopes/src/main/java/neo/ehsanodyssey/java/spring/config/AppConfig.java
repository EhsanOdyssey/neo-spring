package neo.ehsanodyssey.java.spring.config;

import neo.ehsanodyssey.java.spring.data.repository.CustomerRepository;
import neo.ehsanodyssey.java.spring.data.repository.InventoryItemRepository;
import neo.ehsanodyssey.java.spring.data.repository.SalesOrderRepository;
import neo.ehsanodyssey.java.spring.service.InventoryService;
import neo.ehsanodyssey.java.spring.service.OrderService;
import neo.ehsanodyssey.java.spring.service.impl.InventoryServiceImpl;
import neo.ehsanodyssey.java.spring.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@Import(DataConfig.class)
@PropertySource("classpath:/application.properties")
@PropertySource("classpath:/application-${spring.profiles.active}.properties")
public class AppConfig {

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

    // Spring Beans are Java objects that are managed by the Spring container.
    // The Spring Container is responsible for instantiating, configuring, and assembling the Spring beans.
    // 1) Singleton:
    //      The most common bean scope and the one that you get by default, is the [singleton] bean.
    //      And just like its name implies, you get once instance of this bean per application context and as such,
    //      you need to be very careful with singleton beans and the storing of static data within them.
    //      However, because most beans in spring are singletons, it allows you to have a lot of flexibility in
    //      not creating a lot of instances, but having a lot of behavior that is replicated across your application.
    // 2) Prototype:
    //      A [prototype] bean is unique in that you get a new instance of it every time it's referenced.
    //      Its definition is actually stored in the bean factory, but the instances are not, and unlike
    //      a singleton bean that closes down and cleans up its beans through garbage collection as the application
    //      shuts down, a prototype bean is available for garbage collection the minute the instance itself
    //      goes out of scope.
    //      Prototype beans can be very useful for transient data or when you have types that flex based
    //      on application state.
    // If you are in a web app (Spring MVC)
    // 3) Session:
    //      It is used for [web environment] only scope. A [session] scoped bean, as its name implies, allows you
    //      to have one instance of that bean, per user session. So, if you choose to, this allows you to store
    //      session specific data in a bean, knowing that it's going to go out of scope when the session itself
    //      goes out of scope. Once again, a session bean had its definition stored in the bean factory, but
    //      the instance itself is not outside of the session.
    // 4) Request:
    //      It is used for [web environment] only scope. You get once instance of a [request] scope bean
    //      per request and that's a little bit more of a stateless model that we would deal with in
    //      a web environment and once again, just like prototype and session scope beans, the definition
    //      of these is stored in the bean factory, but the instance itself is not and is available for
    //      garbage collection, once it goes out of scope.
    @Bean
	@Scope("prototype")
    public Worker worker() {
        return new Worker(greetingPreamble, greetingText);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Bean
    public OrderService orderService(InventoryService inventoryService, CustomerRepository customerRepository, SalesOrderRepository salesOrderRepository) {
        return new OrderServiceImpl(inventoryService, customerRepository, salesOrderRepository);
    }

    @Bean
    public InventoryService inventoryService(InventoryItemRepository inventoryItemRepository) {
        return new InventoryServiceImpl(inventoryItemRepository);
    }


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService == null ? "NULL" : "A OK");
        Worker worker1 = context.getBean(Worker.class);
        worker1.execute();
        Worker worker2 = context.getBean(Worker.class);
        worker2.execute();
    }
}


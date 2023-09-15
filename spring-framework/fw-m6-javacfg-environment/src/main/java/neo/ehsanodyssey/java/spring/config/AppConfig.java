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
public class AppConfig {
    // If we define "greeting.text" as environment variable in IDE, then we will see in runtime spring will load
    // the mentioned property from IDE environment variable because of spring puts a higher priority on
    // environment variables as opposed to properties files, we actually get that overriding behavior.
    // This priority matrix is actually well documented within Spring, and as you go into more advanced topics
    // of Spring, like Spring Cloud, you actually leverage that cascading properties list in order to override and
    // inject appropriate properties, because surely we wouldn't want into a local properties file.
    // We might want to inject that through an environment variable or some other mechanism that we can be more secure.
    // And Spring allows us to do that through this [cascading matrix].
    // One other final thing to note about properties is we actually can load multiple sources of properties files.
    // And, indeed, that is often what we would do, we might have generic properties that are specific to
    // the application and then properties that are specific to the environment, and then credentials that are specific
    // to the environment properties themselves, and all of those will be in different sources and will load
    // all of those at runtime from all of those various places, and Spring will iron out which ones take priority,
    // and it will populate all placeholders so that you actually get a complete running application.
    @Value("${greeting.text}")
    private String greetingText;

    // This is not a best practice programming, just for example
    public class Worker {
        private String text;

        public Worker(String text) {
            this.text = text;
        }

        public void execute() {
            System.out.println("Hello " + text);
        }
    }

    @Bean
    public Worker worker() {
        return new Worker(greetingText);
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
        Worker worker = context.getBean(Worker.class);
        worker.execute();
    }
}


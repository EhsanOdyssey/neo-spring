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

    @Value("${greeting.text}")
    private String greetingText;

    @Value("${greeting.text.prod}")
    private String greetingTextProd;

    // This is not a best practice programming, just for example
    public class Worker {
        private String preamble;
        private String text;

        public Worker(String preamble, String text) {
            this.preamble = preamble;
            this.text = text;
        }

        public void execute() {
            System.out.println(preamble + " " + text);
        }
    }

    // Spring profiles allow you to load alternate configurations based on environment variables
    // or command-line parameters.
    // Often times when we're dealing with databases, that's really one of the things that we want to do.
    // Because really we have different data sources, but they only differ by a few things.
    // The kicker is that those things that they change or modify by are usually text strings,
    // such as URL or a username.
    @Bean
    @Profile("dev")
    public Worker workerForDev() {
        return new Worker("Hello", greetingText);
    }

    @Bean
    @Profile("prod")
    public Worker workerForProd() {
        return new Worker("Greetings", greetingTextProd);
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


package neo.ehsanodyssey.java.spring.config;

import neo.ehsanodyssey.java.spring.data.repository.CustomerRepository;
import neo.ehsanodyssey.java.spring.data.repository.InventoryItemRepository;
import neo.ehsanodyssey.java.spring.data.repository.SalesOrderRepository;
import neo.ehsanodyssey.java.spring.service.InventoryService;
import neo.ehsanodyssey.java.spring.service.OrderService;
import neo.ehsanodyssey.java.spring.service.impl.InventoryServiceImpl;
import neo.ehsanodyssey.java.spring.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataConfig.class)
public class AppConfig {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Bean
    public OrderService orderService(InventoryService inventoryService, CustomerRepository customerRepository, SalesOrderRepository salesOrderRepository){
        return new OrderServiceImpl(inventoryService, customerRepository, salesOrderRepository);
    }

    @Bean
    public InventoryService inventoryService(InventoryItemRepository inventoryItemRepository){
        return new InventoryServiceImpl(inventoryItemRepository);
    }


    public static void main (String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService==null?"NULL":"A OK");
    }
}


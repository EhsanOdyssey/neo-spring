package neo.ehsanodyssey.java.spring.config;

import neo.ehsanodyssey.java.spring.data.repository.CustomerRepository;
import neo.ehsanodyssey.java.spring.data.repository.InventoryItemRepository;
import neo.ehsanodyssey.java.spring.data.repository.SalesOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Bean
    public InventoryItemRepository inventoryItemRepository(){
        return new InventoryItemRepository();
    }

    @Bean
    public CustomerRepository customerRepository(){
        return new CustomerRepository();
    }

    @Bean
    public SalesOrderRepository salesOrderRepository(){
        return new SalesOrderRepository();
    }
}

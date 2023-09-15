package neo.ehsanodyssey.java.spring.service.impl;

import neo.ehsanodyssey.java.spring.data.repository.CustomerRepository;
import neo.ehsanodyssey.java.spring.data.repository.SalesOrderRepository;
import neo.ehsanodyssey.java.spring.domain.Customer;
import neo.ehsanodyssey.java.spring.domain.Order;
import neo.ehsanodyssey.java.spring.service.InventoryService;
import neo.ehsanodyssey.java.spring.service.OrderService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private final InventoryService inventoryService;
    private final CustomerRepository customerRepository;
    private final SalesOrderRepository salesOrderRepository;

    public OrderServiceImpl(InventoryService inventoryService, CustomerRepository customerRepository, SalesOrderRepository salesOrderRepository) {
        this.inventoryService = inventoryService;
        this.customerRepository = customerRepository;
        this.salesOrderRepository = salesOrderRepository;
    }

    public Order createOrder(Customer customer, Map<String, Long> items) {
        return null;
    }

    public Order createOrder(String customerId, Map<String, Long> items) {
        return null;
    }

    public Order getOrder(String orderId) {
        return null;
    }
}

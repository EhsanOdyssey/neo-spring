package neo.ehsanodyssey.java.spring.service;

import neo.ehsanodyssey.java.spring.domain.Customer;
import neo.ehsanodyssey.java.spring.domain.Order;

import java.util.Map;

public interface OrderService {
    Order createOrder(Customer customer, Map<String, Long> items);
    Order createOrder(String customerId, Map<String, Long> items);
    Order getOrder(String orderId);
}

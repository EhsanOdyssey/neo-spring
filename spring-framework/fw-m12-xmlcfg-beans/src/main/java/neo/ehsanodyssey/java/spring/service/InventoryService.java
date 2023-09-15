package neo.ehsanodyssey.java.spring.service;

import neo.ehsanodyssey.java.spring.domain.Product;

import java.util.Map;

public interface InventoryService {
    Map<Product, Long> getTotalInventoryOnHand();
    long getQuantityOnHand(String itemId);
    void adjustInventory(String itemId, long quantity);
}

package neo.ehsanodyssey.java.spring.service.impl;

import neo.ehsanodyssey.java.spring.data.repository.InventoryItemRepository;
import neo.ehsanodyssey.java.spring.domain.Product;
import neo.ehsanodyssey.java.spring.service.InventoryService;

import java.util.Map;

public class InventoryServiceImpl implements InventoryService {
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public Map<Product, Long> getTotalInventoryOnHand() {
        return null;
    }

    public long getQuantityOnHand(String itemId) {
        return 0;
    }

    public void adjustInventory(String itemId, long quantity) {

    }
}

package com.bandhan.camunda.service.impl;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.entity.Inventory;
import com.bandhan.camunda.repository.InventoryRepo;
import com.bandhan.camunda.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Override
    public boolean isValidInventory(OrderRequest createOrderRequest) {
        Optional<Inventory> inventory = inventoryRepo.findByItemId(createOrderRequest.getItemId());
        if (inventory.isEmpty()) {
            return false;
        }
        Inventory item = inventory.get();
        return item.getInventoryBalance() >= createOrderRequest.getNoOfItems();
    }

    @Override
    public Inventory updateAndGetInventory(int itemId, int noOfItems) throws NotFoundException {
        log.info("Start updating inventory for itemId: {}, noOfItems: {}", itemId, noOfItems);
        Optional<Inventory> inventory = inventoryRepo.findByItemId(itemId);
        if (inventory.isEmpty()) {
            throw new NotFoundException(String.format("Item not found with itemId: %s", itemId));
        }
        Inventory item = inventory.get();
        item.setInventoryBalance(item.getInventoryBalance()-noOfItems);
        item = inventoryRepo.save(item);
        log.info("Inventory updated successfully for itemId: {}, updated inventoryBalance: {}", itemId, item.getInventoryBalance());
        return item;
    }
}

package com.bandhan.camunda.delegate;

import com.bandhan.camunda.entity.Inventory;
import com.bandhan.camunda.repository.InventoryRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("validateInventory")
public class ValidateInventoryDelegate implements JavaDelegate {

    @Autowired
    private InventoryRepo inventoryRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer itemId = (Integer) execution.getVariable("itemId");
        Integer noOfItems = (Integer) execution.getVariable("noOfItems");
        int availableQuantity = checkInventory(itemId);
        boolean isItemPresent = availableQuantity >= noOfItems;
        Inventory inventory = getInventory(itemId);
        execution.setVariable("itemType", inventory.getItemType().name());
        execution.setVariable("isItemPresent", isItemPresent);
    }

    private int checkInventory(Integer itemId) {
        // Convert itemId to an integer if necessary
        Optional<Inventory> inventoryItem = inventoryRepository.findByItemId(itemId);
        return inventoryItem.map(Inventory::getInventoryBalance).orElse(0);  // Return 0 if item not found
    }

    Inventory getInventory(Integer itemId) {
        Optional<Inventory> inventoryItem = inventoryRepository.findByItemId(itemId);
        return inventoryItem.get();
    }
}
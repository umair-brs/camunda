package com.bandhan.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("validateInventory")
public class ValidateInventoryDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Retrieve item details from execution variables
        String itemId = (String) execution.getVariable("itemId");
        Integer noOfItems = (Integer) execution.getVariable("noOfItems");

        // Custom logic to check inventory (assuming available quantity is fetched from database)
        int availableQuantity = checkInventory(itemId);

        boolean isItemPresent = availableQuantity >= noOfItems;

        // Set variable for the process flow
        execution.setVariable("isItemPresent", isItemPresent);
    }

    private int checkInventory(String itemId) {
        // Placeholder: Replace with actual DB call
        return 100;  // Example: assume 100 items available for simplicity
    }
}
package com.bandhan.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("createOrder")
public class CreateOrderDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Retrieve order and inventory details
        String itemId = (String) execution.getVariable("itemId");
        Integer noOfItems = (Integer) execution.getVariable("noOfItems");
        String customerType = (String) execution.getVariable("customerType");

        // Logic to create order in the system (e.g., save to DB)
        createOrderInDatabase(itemId, noOfItems, customerType);

        // Optional: Set variables if needed for further tasks
        execution.setVariable("orderCreated", true);
    }

    private void createOrderInDatabase(String itemId, int noOfItems, String customerType) {
        // Placeholder: replace with actual DB logic to create an order
        System.out.println("Order created for item: " + itemId + ", quantity: " + noOfItems + ", customer type: " + customerType);
    }
}

package com.bandhan.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("createOrder")
public class CreateOrderDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String itemId = (String) execution.getVariable("itemId");
        Integer noOfItems = (Integer) execution.getVariable("noOfItems");
        String customerType = (String) execution.getVariable("customerType");
        createOrderInDatabase(itemId, noOfItems, customerType);
        execution.setVariable("orderCreated", true);
    }

    private void createOrderInDatabase(String itemId, int noOfItems, String customerType) {
        System.out.println("Order created for item: " + itemId + ", quantity: " + noOfItems + ", customer type: " + customerType);
    }
}

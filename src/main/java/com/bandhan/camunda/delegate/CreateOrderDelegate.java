package com.bandhan.camunda.delegate;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.service.OrderService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("createOrder")
public class CreateOrderDelegate implements JavaDelegate {

    @Autowired
    OrderService orderService;

    @Override
    public void execute(DelegateExecution execution) {
        try {
            Integer itemId = (Integer) execution.getVariable("itemId");
            Integer noOfItems = (Integer) execution.getVariable("noOfItems");
            String customerType = (String) execution.getVariable("customerType");
            String userName = (String) execution.getVariable("name");

            // Ensure variables are not null or handle accordingly
            if (itemId == null || noOfItems == null || customerType == null) {
                throw new IllegalArgumentException("One or more required variables are missing.");
            }

            // Call the method to create the order in the database
            createOrderInDatabase(itemId, noOfItems, customerType, userName);

            // Set the process variable to indicate success
            execution.setVariable("orderCreated", true);
        } catch (Exception e) {
            // Log and set a variable to indicate failure, if needed
            execution.setVariable("orderCreated", false);
            execution.setVariable("errorMessage", e.getMessage());
            throw e; // Re-throw if you want Camunda to handle this as an error event
        }
    }

    private void createOrderInDatabase(int itemId, int noOfItems, String customerType, String userName) {
        OrderRequest orderRequest = createOrderRequestObject( itemId,  noOfItems,  customerType, userName);
        orderService.createOrder(orderRequest);
        System.out.println("Order created for item: " + itemId + ", quantity: " + noOfItems + ", customer type: " + customerType);
    }

    OrderRequest createOrderRequestObject(int itemId, int noOfItems, String customerType, String userName){
        OrderRequest orderRequest = new OrderRequest();

        // Set the itemId after parsing it to an integer
        orderRequest.setItemId(itemId);

        // Set the number of items
        orderRequest.setNoOfItems(noOfItems);

        // Initialize and set CustomerDetails
        OrderRequest.CustomerDetails customerDetails = new OrderRequest.CustomerDetails();
        customerDetails.setCustomerType(customerType);
        customerDetails.setName(userName);

        // Set customer details in the order request
        orderRequest.setCustomerDetails(customerDetails);

        return orderRequest;
    }
}

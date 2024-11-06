package com.bandhan.camunda.service.impl;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.entity.Customer;
import com.bandhan.camunda.entity.Inventory;
import com.bandhan.camunda.entity.OrderDetails;
import com.bandhan.camunda.repository.OrderRepo;
import com.bandhan.camunda.service.CustomerService;
import com.bandhan.camunda.service.InventoryService;
import com.bandhan.camunda.service.OrderService;
import com.bandhan.camunda.util.OrderRequestValidator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRequestValidator validator;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RuntimeService runtimeService;
    @Override
    @Transactional
    public void processOrder(OrderRequest request) {
        validator.validate(request);

        Map<String, Object> variables = new HashMap<>();
        variables.put("itemId", request.getItemId());
        variables.put("customerDetails", request.getCustomerDetails());
        variables.put("noOfItems", request.getNoOfItems());
        variables.put("customerType", request.getCustomerDetails().getCustomerType());
        variables.put("name", request.getCustomerDetails().getName());

        runtimeService.startProcessInstanceByKey("processOrder", variables);
    }

    @Override
    public OrderDetails createOrder(OrderRequest createOrderRequest) {
        Customer customer = customerService.getCustomer(createOrderRequest.getCustomerDetails().getName());
        Inventory item = inventoryService.updateAndGetInventory(createOrderRequest.getItemId(), createOrderRequest.getNoOfItems());
        OrderDetails orderDetails = createNewOrder(customer, createOrderRequest, item);
        orderDetails = orderRepo.save(orderDetails);
        return orderDetails;
    }

    private OrderDetails createNewOrder(Customer customer, OrderRequest createOrderRequest, Inventory item) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCustomerId(customer.getId());
        orderDetails.setItemId(item.getItemId());
        orderDetails.setItemQuantity(createOrderRequest.getNoOfItems());
        double orderPrice = item.getPricePerUnit() * createOrderRequest.getNoOfItems();
        orderDetails.setOrderPrice(orderPrice);
        return orderDetails;
    }
}

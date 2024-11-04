package com.bandhan.camunda.service;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.entity.Customer;
import org.camunda.bpm.engine.exception.NotFoundException;

public interface CustomerService {
    boolean isValidCustomer(OrderRequest.CustomerDetails customerDetails);

    Customer getCustomer(String username) throws NotFoundException;
}

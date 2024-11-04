package com.bandhan.camunda.util;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.exception.InvalidOrderException;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestValidator {
    public void validate(OrderRequest request) {
        if (request.getNoOfItems() <= 0) {
            throw new InvalidOrderException("Number of items must be greater than zero.");
        }
        if (request.getCustomerDetails().getMobileNumber().length() != 10) {
            throw new InvalidOrderException("Invalid mobile number.");
        }
        // Additional validations as needed
    }
}

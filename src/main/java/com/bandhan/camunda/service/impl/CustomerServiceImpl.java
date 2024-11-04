package com.bandhan.camunda.service.impl;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.entity.Customer;
import com.bandhan.camunda.repository.CustomerRepo;
import com.bandhan.camunda.service.CustomerService;
import org.camunda.bpm.engine.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public boolean isValidCustomer(OrderRequest.CustomerDetails customerDetails) {
        Optional<Customer> customer = customerRepo.findByUsername(customerDetails.getName());
        return customer.isPresent();
    }

    @Override
    public Customer getCustomer(String username) throws NotFoundException {
        Optional<Customer> customer = customerRepo.findByUsername(username);
        if (customer.isEmpty()) {
            throw new NotFoundException(String.format("Customer not found with username: %s", username));
        }
        return customer.get();
    }
}

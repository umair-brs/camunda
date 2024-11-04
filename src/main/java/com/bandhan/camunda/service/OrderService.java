package com.bandhan.camunda.service;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.entity.OrderDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface OrderService {
    public void processOrder(OrderRequest request);

    OrderDetails createOrder(OrderRequest createOrderRequest);
}

package com.bandhan.camunda.controller;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/process")
    public ResponseEntity<String> processOrder(@RequestBody OrderRequest request) {
        orderService.processOrder(request);
        return ResponseEntity.ok("Order processed successfully");
    }
}
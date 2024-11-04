package com.bandhan.camunda.repository;

import com.bandhan.camunda.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderDetails, Integer> {
}

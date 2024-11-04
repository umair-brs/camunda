package com.bandhan.camunda.service;

import com.bandhan.camunda.dto.OrderRequest;
import com.bandhan.camunda.entity.Inventory;
import org.camunda.bpm.engine.exception.NotFoundException;

public interface InventoryService {
    boolean isValidInventory(OrderRequest createOrderRequest);

    Inventory updateAndGetInventory(int itemId, int noOfItems) throws NotFoundException;
}

package com.bandhan.camunda.repository;

import com.bandhan.camunda.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByItemId(int itemId);
}

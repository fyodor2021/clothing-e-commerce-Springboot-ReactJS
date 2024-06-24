package com.finefoods.inventorymicroservice.repository;

import com.finefoods.inventorymicroservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Inventory findByInventoryId(Long inventoryId);
    Inventory findByProductId(Long itemId);


}

package com.finefoods.inventorymicroservice.service;

import com.finefoods.inventorymicroservice.dto.InventoryRequest;
import com.finefoods.inventorymicroservice.dto.InventoryResponse;
import com.finefoods.inventorymicroservice.dto.ProductAvailability;

import java.util.List;

public interface InventoryService {
    void createInventory(InventoryRequest inventoryRequest);
    void updateInventory(InventoryRequest inventoryRequest);

    void updateInventoryAfterPurchase(InventoryRequest inventoryRequest);
    void updateInventoryAfterCancellation(InventoryRequest inventoryRequest);
    InventoryResponse getInventoryByProductId(long productId);

    Boolean checkInventory(long productId, int quantity);
    void deleteInventory(long productId);
    List<ProductAvailability> areProductsInStock(List<InventoryRequest> inventoryRequests);



}

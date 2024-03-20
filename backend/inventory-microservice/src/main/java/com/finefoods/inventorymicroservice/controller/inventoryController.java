package com.finefoods.inventorymicroservice.controller;

import com.finefoods.inventorymicroservice.dto.InventoryRequest;
import com.finefoods.inventorymicroservice.dto.InventoryResponse;
import com.finefoods.inventorymicroservice.dto.ProductAvailability;
import com.finefoods.inventorymicroservice.service.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class inventoryController {
    private final InventoryServiceImpl inventoryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createInventory(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.createInventory(inventoryRequest);
        return "Inventory was created successfully";
    }
    @PutMapping()
    public void updateInventory(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.updateInventory(inventoryRequest);
    }

    @PutMapping("/purchase")
    public void updateInventoryAfterPurchase(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.updateInventoryAfterPurchase(inventoryRequest);
    }
    @PutMapping("/cancel")
    public void updateInventoryAfterCancellation(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.updateInventoryAfterCancellation(inventoryRequest);
    }
    @GetMapping("/{productId}")
    public InventoryResponse getInventoryByProductId(@PathVariable long productId){
        return inventoryService.getInventoryByProductId(productId);
    }

    @GetMapping("{productId}/{quantity}")
    public Boolean checkInventory(@PathVariable long productId, @PathVariable int quantity){
        return inventoryService.checkInventory(productId, quantity);
    }
    @DeleteMapping("/{productId}")
    public void deleteInventory(@PathVariable long productId){
        inventoryService.deleteInventory(productId);
    }

    @GetMapping("/stock")
    public List<ProductAvailability> areProductsInStock(@RequestBody List<InventoryRequest> inventoryRequests){
        return inventoryService.areProductsInStock(inventoryRequests);
    }

}

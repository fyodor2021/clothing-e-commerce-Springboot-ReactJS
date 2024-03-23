package com.finefoods.inventorymicroservice.service;

import com.finefoods.inventorymicroservice.dto.InventoryRequest;
import com.finefoods.inventorymicroservice.dto.InventoryResponse;
import com.finefoods.inventorymicroservice.model.Inventory;
import com.finefoods.inventorymicroservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final WebClient.Builder webClientBuilder;

    public void createInventory(InventoryRequest inventoryRequest){
        Inventory inventoryExists = inventoryRepository.findByProductId(inventoryRequest.getProductId());
        if (inventoryExists == null){
            Inventory inventory = Inventory.builder().
                    productId(inventoryRequest.getProductId()).stock(0).stockUnit(inventoryRequest.getStockUnit()).build();
            inventoryRepository.save(inventory);
        }
    }
    public void updateInventory(InventoryRequest inventoryRequest){
        Inventory inventoryExists = inventoryRepository.findByProductId(inventoryRequest.getProductId());
        if (inventoryExists != null){
            inventoryExists.setStock(inventoryRequest.getStock());
            inventoryRepository.save(inventoryExists);
        }
    }

    public void updateInventoryAfterPurchase(InventoryRequest inventoryRequest){
        Inventory inventoryExists = inventoryRepository.findByProductId(inventoryRequest.getProductId());
        if (inventoryExists != null){
            float newStock = inventoryExists.getStock() - inventoryRequest.getStock();
            inventoryExists.setStock(newStock);
            inventoryRepository.save(inventoryExists);
        }

    }
    public void updateInventoryAfterCancellation(InventoryRequest inventoryRequest){
        Inventory inventoryExists = inventoryRepository.findByProductId(inventoryRequest.getProductId());
        if (inventoryExists != null){
            float newStock = inventoryExists.getStock() + inventoryRequest.getStock();
            inventoryExists.setStock(newStock);
            inventoryRepository.save(inventoryExists);
        }
    }

    public InventoryResponse getInventoryByProductId(long productId){
        Inventory inventoryExists = inventoryRepository.findByProductId(productId);
        if(inventoryExists != null){
          return inventoryToInventoryResponse(inventoryExists);
        }
        return InventoryResponse.builder().build();
    }
    public Boolean checkInventory(long productId, int quantity ){
        Inventory inventoryExists = inventoryRepository.findByProductId(productId);
        if(inventoryExists != null){
            if (inventoryExists.getStock() >= quantity){
                return true;
            }
        }
        return false;
    }

    public void deleteInventory(long productId){
        Inventory inventoryExists = inventoryRepository.findByProductId(productId);
        if(inventoryExists != null){
            inventoryRepository.deleteById(inventoryExists.getInventoryId());
        }
    }



    private InventoryResponse inventoryToInventoryResponse(Inventory inventory){
        return InventoryResponse.builder()
                .productId(inventory.getProductId())
                .stock(inventory.getStock())
                .stockUnit(inventory.getStockUnit()).build();
    }

}


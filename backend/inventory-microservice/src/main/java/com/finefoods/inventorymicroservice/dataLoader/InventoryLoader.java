package com.finefoods.inventorymicroservice.dataLoader;

import com.finefoods.inventorymicroservice.model.Inventory;
import com.finefoods.inventorymicroservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryLoader implements CommandLineRunner {
    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (inventoryRepository.findByInventoryId(1L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(1L)
                    .productId(1)
                    .stock(20.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(2L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(2L)
                    .productId(2)
                    .stock(10.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(3L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(3L)
                    .productId(3)
                    .stock(5.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(4L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(4L)
                    .productId(4)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(5L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(5L)
                    .productId(5)
                    .stock(30.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(6L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(6L)
                    .productId(6)
                    .stock(25.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(7L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(7L)
                    .productId(7)
                    .stock(40.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(8L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(8L)
                    .productId(8)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(9L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(9L)
                    .productId(9)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(10L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(10L)
                    .productId(10)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(11L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(11L)
                    .productId(11)
                    .stock(10.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(12L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(12L)
                    .productId(12)
                    .stock(15.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(13L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(13L)
                    .productId(13)
                    .stock(10.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(14L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(14L)
                    .productId(14)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(15L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(15L)
                    .productId(15)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(16L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(16L)
                    .productId(16)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(17L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(17L)
                    .productId(17)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(18L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(18L)
                    .productId(18)
                    .stock(30.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(19L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(19L)
                    .productId(19)
                    .stock(20.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(20L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(20L)
                    .productId(20)
                    .stock(10.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(21L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(21L)
                    .productId(21)
                    .stock(20.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(22L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(22L)
                    .productId(22)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(23L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(23L)
                    .productId(23)
                    .stock(70.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(24L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(24L)
                    .productId(24)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(25L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(25L)
                    .productId(25)
                    .stock(20.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(26L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(26L)
                    .productId(26)
                    .stock(30.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(27L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(27L)
                    .productId(27)
                    .stock(80.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(28L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(28L)
                    .productId(28)
                    .stock(40.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(29L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(29L)
                    .productId(29)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(30L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(30L)
                    .productId(30)
                    .stock(30.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(31L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(31L)
                    .productId(31)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(32L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(32L)
                    .productId(32)
                    .stock(10.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(33L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(33L)
                    .productId(33)
                    .stock(20.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(34L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(34L)
                    .productId(34)
                    .stock(40.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(35L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(35L)
                    .productId(35)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(36L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(36L)
                    .productId(36)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(37L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(37L)
                    .productId(37)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(38L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(38L)
                    .productId(38)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(39L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(39L)
                    .productId(39)
                    .stock(50.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }
        if (inventoryRepository.findByInventoryId(40L) == null)
        {
            Inventory inventory = Inventory.builder()
                    .inventoryId(40L)
                    .productId(40)
                    .stock(20.0f)
                    .stockUnit("pieces")
                    .build();
            inventoryRepository.save(inventory);
        }






    }
}

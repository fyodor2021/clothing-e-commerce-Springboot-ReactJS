package com.finefoods.inventorymicroservice.service;

import com.finefoods.inventorymicroservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl {
    private final InventoryRepository inventoryRepository;
    private final WebClient.Builder webClientBuilder;
}

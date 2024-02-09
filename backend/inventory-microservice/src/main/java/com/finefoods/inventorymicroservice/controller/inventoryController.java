package com.finefoods.inventorymicroservice.controller;

import com.finefoods.inventorymicroservice.service.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class inventoryController {
    private final InventoryServiceImpl inventoryService;
}

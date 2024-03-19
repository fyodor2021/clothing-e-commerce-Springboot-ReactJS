package com.finefoods.inventorymicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private long productId;
    private float stock;
    private String stockUnit;

}

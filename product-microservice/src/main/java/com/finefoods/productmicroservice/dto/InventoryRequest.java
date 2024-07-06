package com.finefoods.productmicroservice.dto;

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
    private long smallStock;
    private long mediumStock;
    private long largeStock;
    private long xlStock;

}

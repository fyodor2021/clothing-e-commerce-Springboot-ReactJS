package com.finefoods.productmicroservice.service;

import com.finefoods.productmicroservice.dto.ProductRequest;
import com.finefoods.productmicroservice.dto.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    Boolean creatProduct (ProductRequest productRequest);
    ResponseEntity<ProductResponse> getProduct(Long id);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(String category);
    List<ProductResponse> getProductsBySearchTerm(String word);
    List<Boolean> validateProductList(List<ProductRequest> products );
    Boolean updateProduct(Long id, ProductRequest productRequest);
    Boolean deleteProduct(Long id);
}

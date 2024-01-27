package com.finefoods.productmicroservice.service;

import com.finefoods.productmicroservice.dto.ProductRequest;
import com.finefoods.productmicroservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    Boolean creatProduct (ProductRequest productRequest);
    ProductResponse getProduct(Long id);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(String category);
    List<ProductResponse> getProductsBySearchTerm(String word);
    List<ProductResponse> getProductsBySortOrder(String sortOrder);
    List<Boolean> validateProductList(List<ProductRequest> products );
    Boolean updateProduct(Long id, ProductRequest productRequest);
    Boolean deleteProduct(Long id);

}

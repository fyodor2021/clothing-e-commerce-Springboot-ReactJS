package com.finefoods.productmicroservice.service;

import com.finefoods.productmicroservice.dto.ProductRequest;
import com.finefoods.productmicroservice.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Boolean creatProduct (MultipartFile[] files, ProductRequest productRequest);
    ResponseEntity<ProductResponse> getProduct(Long id) throws IOException;
    List<ProductResponse> getAllProducts() throws IOException;
    ProductResponse validateProduct(Long id);
//    List<ProductResponse> getProductsByCategory(String category);
    List<ProductResponse> getProductsBySearchTerm(String word) throws IOException;
    List<ProductResponse> getProductByCategory(String category) throws IOException;
//    List<ProductResponse> validateProductList(List<ProductRequest> products );
//    Boolean updateProduct(Long id, ProductRequest productRequest);
    Boolean deleteProduct(Long id) throws IOException;
    List<String> searchPrediction(String Search);
}

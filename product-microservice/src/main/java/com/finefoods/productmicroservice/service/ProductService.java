package com.finefoods.productmicroservice.service;

import com.finefoods.productmicroservice.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ResponseEntity<ProductResponse> getProduct(Long id) throws IOException;
    List<ProductResponse> getAllProducts() throws IOException;
    List<ProductResponse> getProductsBySearchTerm(String word) throws IOException;
    List<OrderProductResponse> getCartProductList(CartProductReq cartProductReq) throws IOException;
}

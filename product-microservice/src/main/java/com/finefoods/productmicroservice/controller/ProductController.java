package com.finefoods.productmicroservice.controller;

import com.finefoods.productmicroservice.dto.ProductRequest;
import com.finefoods.productmicroservice.dto.ProductResponse;
import com.finefoods.productmicroservice.service.ProductServiceImp;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/product")

public class ProductController {

    private final ProductServiceImp productService;
    public Boolean creatProduct (ProductRequest productRequest){return null;}
    public ProductResponse getProduct(Long id){return null;}
    public List<ProductResponse> getAllProducts(){return null;}
    public List<ProductResponse> getProductsByCategory(String category){return null;}
    public List<ProductResponse> getProductsBySearchTerm(String word){return null;}
    public List<ProductResponse> getProductsBySortOrder(String sortOrder){return null;}
    public List<Boolean> validateProductList(List<ProductRequest> products ){return null;}
    public Boolean updateProduct(Long id, ProductRequest productRequest){return null;}
    public Boolean deleteProduct(Long id){return null;}

}

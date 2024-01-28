package com.finefoods.productmicroservice.controller;

import com.finefoods.productmicroservice.dto.ProductRequest;
import com.finefoods.productmicroservice.dto.ProductResponse;
import com.finefoods.productmicroservice.service.ProductServiceImp;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/product")

public class ProductController {

    private final ProductServiceImp productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String creatProduct (@RequestBody ProductRequest productRequest){
        Boolean bool = productService.creatProduct(productRequest);
        if (bool){
            return "Product was added successfully";
        }
        return "Error occurred";
    }

    @GetMapping({"/{id}"})
    public ProductResponse getProduct(@PathVariable Long id){
        ProductResponse pr = productService.getProduct(id);
        if(pr != null ){
            return pr;
        }
        return null;
    }
    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping({""})
    public List<ProductResponse> getProductsByCategory(@RequestParam("category") String category){
        return productService.getProductsByCategory(category);
    }
    public List<ProductResponse> getProductsBySearchTerm(String word){
        return productService.getProductsBySearchTerm(word);
    }
    public List<ProductResponse> getProductsBySortOrder(String sortOrder){
        return  productService.getProductsBySortOrder(sortOrder);
    }
    public List<Boolean> validateProductList(List<ProductRequest> products ){
        return productService.validateProductList(products);
    }
    @PutMapping({"/{productId}"})
    public String updateProduct(Long id, ProductRequest productRequest){
        Boolean bool = productService.updateProduct(id,productRequest);
        if (bool){
            return "Product was updated successfully";
        }
        return "Error occurred";
    }
    @DeleteMapping({"/{productId}"})
    public String deleteProduct(Long id) {
        Boolean bool = productService.deleteProduct(id);
        if (bool) {
            return "Product was deleted successfully";
        }
        return "Error occurred";
    }
}

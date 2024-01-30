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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }
    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }


    @GetMapping({"category"})
    public List<ProductResponse> getProductsByCategory(@RequestParam("category") String category){
        return productService.getProductsByCategory(category);
    }
    @GetMapping({"search"})
    public List<ProductResponse> getProductsBySearchTerm(@RequestParam("search") String word){
        return productService.getProductsBySearchTerm(word);
    }


    @GetMapping({"validate"})
    public List<Boolean> validateProductList(@RequestBody List<ProductRequest> products ){
        return productService.validateProductList(products);
    }
    @PutMapping({"/{id}"})
    public String updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        Boolean bool = productService.updateProduct(id,productRequest);
        if (bool){
            return "Product was updated successfully";
        }
        return "Error occurred";
    }
    @DeleteMapping({"/{id}"})
    public String deleteProduct(@PathVariable Long id) {
        Boolean bool = productService.deleteProduct(id);
        if (bool) {
            return "Product was deleted successfully";
        }
        return "Error occurred";
    }
}

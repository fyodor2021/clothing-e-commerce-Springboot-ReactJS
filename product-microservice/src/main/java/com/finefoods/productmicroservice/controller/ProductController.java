package com.finefoods.productmicroservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefoods.productmicroservice.dto.*;
import com.finefoods.productmicroservice.model.Image;
import com.finefoods.productmicroservice.repository.ImageRepository;
import com.finefoods.productmicroservice.service.ProductServiceImp;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductController {
    private final ProductServiceImp productService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String creatProduct (@RequestParam("files") MultipartFile[] files,
    @RequestParam("productRequest") String productRequestJson )
     throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequest productRequest = objectMapper.readValue(productRequestJson, ProductRequest.class);
        Boolean bool = productService.creatProduct(files, productRequest);
        if (bool){
            return "Product was added successfully";
        }
        return "Error occurred";
    }
    @GetMapping("/{id}")
    public ProductResponse validateProduct(@PathVariable Long id){
        return productService.validateProduct(id);
    }
    @GetMapping({"/image/{id}"})
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) throws IOException {
        return productService.getProduct(id);
    }
    @GetMapping
    public List<ProductResponse> getAllProducts() throws IOException {
        return productService.getAllProducts();
    }
    @GetMapping("/search/{input}")
    public List<ProductFilterResponse> searchPrediction(@PathVariable String input){
        return productService.searchPrediction(input);
    }

//    @GetMapping({"category/{category}"})
//    public List<ProductResponse> getProductsByCategory(@PathVariable("category") String category){
//        return productService.getProductsByCategory(category);
//    }
    @GetMapping({"search/submit/{search}"})
    public List<ProductResponse> getProductsBySearchTerm(@PathVariable("search") String search) throws IOException {
        return productService.getProductsBySearchTerm(search);
    }
    @PostMapping("/order/products")
    List<OrderProductResponse> getProductsByProductIdList(@RequestBody List<CartProductDesc> cartProductDescs) throws IOException{
        return productService.getProductsByProductIdList(cartProductDescs);
    }
    @GetMapping({"search/category/{search}"})
    public List<ProductResponse> getProductByCategory(@PathVariable("search") String search) throws IOException {
        return productService.getProductByCategory(search);
    }
//
//    @GetMapping({"validate"})
//    public List<ProductResponse> validateProductList(@RequestBody List<ProductRequest> products ){
//        return productService.validateProductList(products);
//    }
//    @PutMapping({"/{id}"})
//    public String updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
//        Boolean bool = productService.updateProduct(id,productRequest);
//        if (bool){
//            return "Product was updated successfully";
//        }
//        return "Error occurred";
//    }
    @DeleteMapping({"/{id}"})
    public String deleteProduct(@PathVariable Long id) throws IOException {
        Boolean bool = productService.deleteProduct(id);
        if (bool) {
            return "Product was deleted successfully";
        }
        return "Error occurred";
    }
}

package com.finefoods.productmicroservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefoods.productmicroservice.dto.*;
import com.finefoods.productmicroservice.service.ProductServiceImp;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductController {
    private final ProductServiceImp productService;

    @GetMapping({"/image/{id}"})
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) throws IOException {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() throws IOException {
        return productService.getAllProducts();
    }

    @PostMapping({"/list"})
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name="cartList", fallbackMethod = "getCartProductListFallBack")
    public ResponseEntity<?> getCartProductList(@RequestBody CartProductReq cartProductReq) throws IOException {
        return new ResponseEntity<>(productService.getCartProductList(cartProductReq),HttpStatus.OK);
    }
    public ResponseEntity<?> getCartProductListFallBack(CartProductReq cartProductReq,Exception e) {
        return new ResponseEntity<>("Couldn't get logged user due to " + e.getMessage()
                + "please try again later", HttpStatus.REQUEST_TIMEOUT);
    }

    @GetMapping({"search/{search}"})
    public List<ProductResponse> getProductsBySearchTerm(@PathVariable("search") String search) throws IOException {
        return productService.getProductsBySearchTerm(search);
    }
}

package com.finefoods.productmicroservice.service;

import com.finefoods.productmicroservice.dto.*;
import com.finefoods.productmicroservice.model.Image;
import com.finefoods.productmicroservice.model.Product;
import com.finefoods.productmicroservice.repository.ImageRepository;
import com.finefoods.productmicroservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    private final ProductHelper productHelper;



    @Override
    public ResponseEntity<ProductResponse> getProduct(Long id) throws IOException {
        List<Image> imageList = imageRepository.getImagesByProductId(id);
        List<URL> imageUrls = new ArrayList<>();
        for (Image image : imageList) {
            imageUrls.add(productHelper.getImage(image.getImageFileName()));
        }

        Product product = productRepository.findProductByProductId(id);
        if (product != null) {
            ProductResponse productResponse = productHelper.productToProductResponse(product, imageUrls);
            return ResponseEntity.ok(productResponse);
        }
        ProductResponse emptyObject = ProductResponse.builder().build();
        return ResponseEntity.ok(emptyObject);

    }
    @Override
    public List<OrderProductResponse> getCartProductList(CartProductReq cartProductReq) throws IOException {
        if(cartProductReq.getUserEmail() == null || cartProductReq.getUserEmail().isEmpty()){
            if(cartProductReq.getCartProducts() != null && !cartProductReq.getCartProducts().isEmpty()){
                return productHelper.productListToProducts(cartProductReq.getCartProducts());
            }else{
                return new ArrayList<>();
            }
        }else{
            if(cartProductReq.getCartProducts() != null && !cartProductReq.getCartProducts().isEmpty()){
                List<CartProduct> cartProducts =
                        productHelper.getCartItems(cartProductReq.getUserEmail());
                cartProducts.addAll(cartProductReq.getCartProducts());
                return productHelper.productListToProducts(cartProducts);
            }else{
                return productHelper.productListToProducts(
                        productHelper.getCartItems(cartProductReq.getUserEmail()));
            }
        }
    }

    @Override
    public List<ProductResponse> getAllProducts() throws IOException {
        List<Product> products = productRepository.findAll();
        return productHelper.mapToProductImageResponse(products,true);
    }
    @Override
    public List<ProductResponse> getProductsBySearchTerm(String word) throws IOException {
        List<Product> products = productRepository.findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(word, word);
        return productHelper.mapToProductImageResponse(products, true);
    }


}
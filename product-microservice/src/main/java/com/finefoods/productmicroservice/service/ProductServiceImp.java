package com.finefoods.productmicroservice.service;

import com.finefoods.productmicroservice.dto.ProductRequest;
import com.finefoods.productmicroservice.dto.ProductResponse;
import com.finefoods.productmicroservice.model.Product;
import com.finefoods.productmicroservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor

public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Boolean creatProduct (ProductRequest productRequest){
        Product product = Product.builder()
                .picture(productRequest.getPicture())
                .brand(productRequest.getBrand())
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .category(productRequest.getCategory())
                .tags(productRequest.getTags())
                .size(productRequest.getSize())
                .unit(productRequest.getUnit())
                .cost(productRequest.getCost())
                .currentPrice(productRequest.getCurrentPrice())
                .isTaxed(productRequest.getIsTaxed())
                .skuCode(productRequest.getSkuCode())
                .upcCode(productRequest.getUpcCode())
                .vendor(productRequest.getVendor())
                .build();

        productRepository.save(product);

        return Boolean.TRUE;
    }
    @Override
    public ResponseEntity<ProductResponse> getProduct(Long id){
        Product p = productRepository.findProductByProductId(id);
        if(p != null ){
            ProductResponse pr = productToProductResponse(p);
            return ResponseEntity.ok(pr);
        }
        ProductResponse emptyObject = ProductResponse.builder().build();
        return ResponseEntity.ok(emptyObject);

    }
    @Override
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::productToProductResponse).toList();


    }
    @Override
    public List<ProductResponse> getProductsByCategory(String category){
        List<Product> products = productRepository.findProductByCategory(category);
        return products.stream().map(this::productToProductResponse).toList();

    }

    @Override
    public List<ProductResponse> getProductsBySearchTerm(String word){
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(word);
        return products.stream().map(this::productToProductResponse).toList();

    }
    @Override
    public List<ProductResponse> validateProductList(List<ProductRequest> products ){
        return products.stream().map(this::doesExist).toList();
    }

    private ProductResponse doesExist(ProductRequest p){
        Product pp = productRepository.findProductByProductId(p.getProductId());
        if(pp != null){
            return productToProductResponse(pp);
        }
        else {
            return ProductResponse.builder().build();
        }

    }
    @Override
    public Boolean updateProduct(Long id, ProductRequest p){
        Product product = productRepository.findProductByProductId(id);
        if (product != null){
            product.setPicture(p.getPicture());
            product.setBrand(p.getBrand());
            product.setProductName(p.getProductName());
            product.setDescription(p.getDescription());
            product.setCategory(p.getCategory());
            product.setTags(p.getTags());
            product.setSize(p.getSize());
            product.setUnit(p.getUnit());
            product.setCost(p.getCost());
            product.setCurrentPrice(p.getCurrentPrice());
            product.setIsTaxed(p.getIsTaxed());
            product.setSkuCode(p.getSkuCode());
            product.setUpcCode(p.getUpcCode());
            product.setVendor(p.getVendor());

            productRepository.save(product);
            return true;
        }
        return false;
    }


    @Override
    public Boolean deleteProduct(Long id){
        Product p = productRepository.findProductByProductId(id);
        if (p != null){
            productRepository.deleteById(id);
            return true;
        }
       return false;
    }

    private ProductResponse productToProductResponse(Product p){
        return ProductResponse.builder()
                .productId(p.getProductId())
                .picture(p.getPicture())
                .brand(p.getBrand())
                .productName(p.getProductName())
                .description(p.getDescription())
                .category(p.getCategory())
                .tags(p.getTags())
                .size(p.getSize())
                .unit(p.getUnit())
                .cost(p.getCost())
                .currentPrice(p.getCurrentPrice())
                .isTaxed(p.getIsTaxed())
                .skuCode(p.getSkuCode())
                .upcCode(p.getUpcCode())
                .vendor(p.getVendor())
                .build();
    }

}

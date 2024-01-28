package com.finefoods.productmicroservice.service;

import com.finefoods.productmicroservice.dto.ProductRequest;
import com.finefoods.productmicroservice.dto.ProductResponse;
import com.finefoods.productmicroservice.model.Product;
import com.finefoods.productmicroservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
                .name(productRequest.getName())
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
    public ProductResponse getProduct(Long id){
        Product p = productRepository.findProductById(id);
        return productToProductResponse(p);
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
    public List<ProductResponse> getProductsBySortOrder(String sortOrder){
        return null;
    }
    @Override
    public List<Boolean> validateProductList(List<ProductRequest> products ){
        return products.stream().map(this::doesExist).toList();
    }

    private Boolean doesExist(ProductRequest p){
        Product pp = productRepository.findProductById(p.getId());
        if(pp == null){
            return false;
        }
        else {
            return true;
        }

    }
    @Override
    public Boolean updateProduct(Long id, ProductRequest p){
        Product product = productRepository.findProductById(id);
        if (product != null){
            product.setPicture(p.getPicture());
            product.setBrand(p.getBrand());
            product.setName(p.getName());
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
        Product p = productRepository.findProductById(id);
        if (p != null){
            productRepository.deleteProductById(id);
            return true;
        }
       return false;
    }

    private ProductResponse productToProductResponse(Product p){
        return ProductResponse.builder()
                .picture(p.getPicture())
                .brand(p.getBrand())
                .name(p.getName())
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

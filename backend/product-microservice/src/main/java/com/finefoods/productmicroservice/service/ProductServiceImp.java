package com.finefoods.productmicroservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefoods.productmicroservice.dto.InventoryRequest;
import com.finefoods.productmicroservice.dto.ProductRequest;
import com.finefoods.productmicroservice.dto.ProductResponse;
import com.finefoods.productmicroservice.model.Image;
import com.finefoods.productmicroservice.model.Product;
import com.finefoods.productmicroservice.repository.ImageRepository;
import com.finefoods.productmicroservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ObjectMapper objectMapper;

    private final AmazonS3 amazonS3Client;
    @Value("${aws.bucket.name}")
    private String bucketName;

    private final WebClient.Builder webClient;
    @Value("${inventory.service.url}")
    private String inventoryUri;
    @Override
    public Boolean creatProduct(MultipartFile[] files, ProductRequest productRequest) {
        Product product = Product.builder()
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

        Product savedProduct  = productRepository.save(product);
        uploadImages(files, savedProduct);

        // Creating Inventory for the product that was created
        InventoryRequest inventoryRequest = InventoryRequest.builder()
                .productId(product.getProductId())
                .stockUnit(product.getUnit()).build();

                webClient.build().post()
                        .uri(inventoryUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(inventoryRequest)
                        .retrieve()
                        .bodyToMono(String.class)
                        .subscribe(responseBody -> {
                            System.out.println("Response: " + responseBody);
                        }, error -> {
                            System.err.println("Error: " + error.getMessage());
                        });


        return Boolean.TRUE;
    }
    @Override
    public ProductResponse validateProduct(Long id){
        Product product = productRepository.findProductByProductId(id);
        if(product != null ){
            return mapToProductResponseValidate(product);
        }
        return ProductResponse.builder().build();
    }
    private ProductResponse mapToProductResponseValidate(Product product){
        return ProductResponse.builder()
                .productId(product.getProductId())
                .brand(product.getBrand())
                .productName(product.getProductName())
                .description(product.getDescription())
                .category(product.getCategory())
                .tags(product.getTags())
                .size(product.getSize())
                .unit(product.getUnit())
                .cost(product.getCost())
                .currentPrice(product.getCurrentPrice())
                .isTaxed(product.getIsTaxed())
                .skuCode(product.getSkuCode())
                .upcCode(product.getUpcCode())
                .vendor(product.getVendor())
                .build();
    }

    @Override
    public ResponseEntity<ProductResponse> getProduct(Long id) throws IOException {
        List<Image> imageList = imageRepository.getImageByProductId(id);
        List<byte[]> imagesInBytes = new ArrayList<>();
        for(Image image: imageList){
            imagesInBytes.add(getImage(image.getImageFileName()));
        }

        Product product = productRepository.findProductByProductId(id);
        if(product != null ){
            ProductResponse productResponse = productToProductResponse(product,imagesInBytes);
            return ResponseEntity.ok(productResponse);
        }
        ProductResponse emptyObject = ProductResponse.builder().build();
        return ResponseEntity.ok(emptyObject);

    }
    @Override
    public List<ProductResponse> getAllProducts() throws IOException {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product: products){
            List<Image> imageList = imageRepository.getImageByProductId(product.getProductId());
            List<byte[]> imagesInBytes = new ArrayList<>();
            for(Image image: imageList){
                imagesInBytes.add(getImage(image.getImageFileName()));
            }
            productResponses.add(productToProductResponse(product, imagesInBytes));
        }
        return productResponses;
    }
//    @Override
//    public List<ProductResponse> getProductsByCategory(String category){
//        List<Product> products = productRepository.findProductByCategory(category);
//        return products.stream().map(this::productToProductResponse).toList();
//
//    }
//
//    @Override
//    public List<ProductResponse> getProductsBySearchTerm(String word){
//        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(word);
//        return products.stream().map(this::productToProductResponse).toList();
//
//    }
//    @Override
//    public List<ProductResponse> validateProductList(List<ProductRequest> products ){
//        return products.stream().map(this::doesExist).toList();
//    }
//
//    private ProductResponse doesExist(ProductRequest p){
//        Product pp = productRepository.findProductByProductId(p.getProductId());
//        if(pp != null){
//            return productToProductResponse(pp);
//        }
//        else {
//            return ProductResponse.builder().build();
//        }
//
//    }
//    @Override
//    public Boolean updateProduct(Long id, ProductRequest p){
//        Product product = productRepository.findProductByProductId(id);
//        if (product != null){
//            product.setBrand(p.getBrand());
//            product.setProductName(p.getProductName());
//            product.setDescription(p.getDescription());
//            product.setCategory(p.getCategory());
//            product.setTags(p.getTags());
//            product.setSize(p.getSize());
//            product.setUnit(p.getUnit());
//            product.setCost(p.getCost());
//            product.setCurrentPrice(p.getCurrentPrice());
//            product.setIsTaxed(p.getIsTaxed());
//            product.setSkuCode(p.getSkuCode());
//            product.setUpcCode(p.getUpcCode());
//            product.setVendor(p.getVendor());
//
//            productRepository.save(product);
//            return true;
//        }
//        return false;
//    }


    @Override
    public Boolean deleteProduct(Long id) throws IOException {
        Product p = productRepository.findProductByProductId(id);
        List<Image> imageList = imageRepository.getImageByProductId(id);
        for(Image image: imageList){
            deleteImage(image.getImageFileName());
        }
        if (p != null){
            productRepository.deleteById(id);
            return true;
        }
       return false;
    }

    private ProductResponse productToProductResponse(Product product, List<byte[]> imageList){
        return ProductResponse.builder()
                .productId(product.getProductId())
                .imageList(imageList)
                .brand(product.getBrand())
                .productName(product.getProductName())
                .description(product.getDescription())
                .category(product.getCategory())
                .tags(product.getTags())
                .size(product.getSize())
                .unit(product.getUnit())
                .cost(product.getCost())
                .currentPrice(product.getCurrentPrice())
                .isTaxed(product.getIsTaxed())
                .skuCode(product.getSkuCode())
                .upcCode(product.getUpcCode())
                .vendor(product.getVendor())
                .build();
    }
    private void uploadImages(MultipartFile[] files, Product product){
        for(MultipartFile file : files){
            File fileObj=convertMultiToFile(file);
            String fileName = product.getSkuCode() + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Image image = Image.builder().imageFileName(fileName).productId(product.getProductId()).build();
            Image savedImage = imageRepository.save(image);
            amazonS3Client.putObject(new PutObjectRequest(bucketName,fileName, fileObj));
            fileObj.delete();
        }
    }
    private File convertMultiToFile(MultipartFile file){
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }catch (IOException e){
            log.error("Error Converting file", e);
        }
        return convertedFile;
    }
    public byte[] getImage(String fileName) throws IOException {

        S3Object s3Object = amazonS3Client.getObject(bucketName,fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        return IOUtils.toByteArray(inputStream);
    }
    public void deleteImage(String filename) throws IOException{
        amazonS3Client.deleteObject(bucketName, filename);
    }
}

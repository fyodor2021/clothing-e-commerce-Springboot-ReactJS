package com.finefoods.productmicroservice.service;

import com.finefoods.productmicroservice.dto.CartProduct;
import com.finefoods.productmicroservice.dto.OrderProductResponse;
import com.finefoods.productmicroservice.dto.ProductResponse;
import com.finefoods.productmicroservice.model.Image;
import com.finefoods.productmicroservice.model.Product;
import com.finefoods.productmicroservice.repository.ImageRepository;
import com.finefoods.productmicroservice.repository.ProductRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@Service
public class ProductHelper {

    private final ImageRepository imageRepository;
    private final WebClient.Builder webClient;
    private final ProductRepository productRepository;
    @Value("${gc.bucket.name}")
    private String bucketName;
    @Autowired
    private Storage storage;

    @Value("${order-microservice.url}")
    private String orderUri;

    public ProductResponse productToProductResponse(Product product, List<URL> imageList) {
        return ProductResponse.builder().productId(product.getProductId()).imageList(imageList).brand(product.getBrand()).productName(product.getProductName()).description(product.getDescription()).category(product.getCategory()).tags(product.getTags()).gender(product.getGender()).color(product.getColor()).cost(product.getCost()).price(product.getPrice()).points(product.getPoints()).currentPrice(product.getCurrentPrice()).isTaxed(product.getIsTaxed()).skuCode(product.getSkuCode()).upcCode(product.getUpcCode()).vendor(product.getVendor()).points(product.getPoints()).build();
    }


    public List<ProductResponse> mapToProductImageResponse(List<Product> products, boolean firstPicOnly) throws IOException {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            List<Image> imageList = imageRepository.getImagesByProductId(product.getProductId());
            List<URL> imageUrls = new ArrayList<>();
            if (firstPicOnly) {
                if (imageList != null && !imageList.isEmpty()) {
                    imageUrls.add(getImage(imageList.get(0).getImageFileName()));
                }
            } else {
                if (imageList != null && !imageList.isEmpty()) {
                    for (Image image : imageList) {
                        imageUrls.add(getImage(image.getImageFileName()));
                    }
                }
            }
            productResponses.add(productToProductResponse(product, imageUrls));
        }
        return productResponses;
    }

    public URL getImage(String fileName) throws IOException {
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, fileName)).build();
        return storage.signUrl(blobInfo, 24, TimeUnit.HOURS, Storage.SignUrlOption.withV4Signature());
    }

    public void imageLoader(File file, Product product) {
        String fileName = file.getName();
        Image image = Image.builder().imageFileName(fileName).productId(product.getProductId()).build();
        Image savedImage = imageRepository.save(image);
    }

    public List<CartProduct> getCartItems(String userEmail) throws IOException {
        return webClient.build().post().uri(orderUri + "/cart/products").contentType(MediaType.APPLICATION_JSON).bodyValue(userEmail).retrieve().bodyToMono(new ParameterizedTypeReference<List<CartProduct>>() {
        }).block();
    }

    public List<OrderProductResponse> productListToProducts(List<CartProduct> cartProducts) throws IOException {
        if(cartProducts == null|| cartProducts.isEmpty()) {
            return new ArrayList<>();
        }else{
            List<OrderProductResponse> orderProductResponses = new ArrayList<>();
            for (CartProduct cartProduct : cartProducts) {
                Product product = productRepository.findProductByProductId(cartProduct.getProductId());
                Image imageList = imageRepository.getFirstByProductId(cartProduct.getProductId());
                URL imageUrl = getImage(imageList.getImageFileName());
                orderProductResponses.add(OrderProductResponse.builder().productId(product.getProductId()).productName(product.getProductName()).size(cartProduct.getSize()).quantity(cartProduct.getQuantity()).description(product.getDescription()).imageUrl(imageUrl).upcCode(product.getUpcCode()).price(product.getCurrentPrice()).build());
            }
            return orderProductResponses;
        }
    }
}

package com.finefoods.cartmicroservice.service;


import com.finefoods.cartmicroservice.dto.*;
import com.finefoods.cartmicroservice.model.Cart;
import com.finefoods.cartmicroservice.model.Product;
import com.finefoods.cartmicroservice.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImp implements CartService {
    final CartRepository cartRepository;
    private final WebClient.Builder webClientBuilder;
    @Value("${inventory.microservice.url}")
    private String inventoryUri;
    public Cart createCart(String headerValue){
                    Cart cart = Cart.builder()
                    .headerValue(headerValue)
                    .products(new ArrayList<>()).build();
            return cartRepository.save(cart);
//        if()
//        {
//            Cart cart = Cart.builder()
//                    .userId(null)
//                    .sessionId(sessionId)
//                    .products(new ArrayList<>()).build();
//            cartRepository.save(cart);
//        }else{
//            Cart cart = Cart.builder()
//                    .userId(addToCartRequest.getUserId())
//                    .products(new ArrayList<>()).build();
//            cartRepository.save(cart);
//        }
    }

    public void addToCart(AddToCartRequest addToCartRequest,String headerValue){
        Cart cart;
        if(!headerValue.contains("@")){
            Cart cartLookup = cartRepository.findCartByHeaderValue(headerValue);
            if(cartLookup == null){
                cart = createCart(headerValue);
            }else{
                cart = cartLookup;
            }
        }else{
            cart = cartRepository.findCartByHeaderValue(headerValue);
        }
        if(cart != null){
            List<Product> productsInCart = cart.getProducts();
            for(Product product : productsInCart){
                if(product.getProductId().equals(addToCartRequest.getProduct().getProductId())
                && addToCartRequest.getProduct().getSize().equals(product.getSize())
                ){
                    product.setQuantity(product.getQuantity() + 1);
                    addToCartRequest.setProduct(null);
                    break;
                }
            }
            if(addToCartRequest.getProduct() != null){
                productsInCart.add(addToCartRequest.getProduct());
                cart.setProducts(productsInCart);
                cartRepository.save(cart);
            }else{
                cart.setProducts(productsInCart);
                cartRepository.save(cart);
            }
        }

//        Cart guestCart = null;
//        if(addToCartRequest.getSessionId() != null){
//            guestCart = cartRepository.findCartBySessionId(session.getId());
//        } else if (addToCartRequest.getUserId() != null) {
//            guestCart = cartRepository.findCartByUserId(addToCartRequest.getUserId());
//        }
//        if(guestCart == null){
//            createCart(addToCartRequest, session.getId());
//        }else{
//            List<Product> productsInCart = guestCart.getProducts();
//            for(Product product : productsInCart){
//                if(product.getProductId() == addToCartRequest.getProduct().getProductId()){
//                    product.setQuantity(product.getQuantity() + 1);
//                    addToCartRequest.setProduct(null);
//                    break;
//                }
//            }
//            if(addToCartRequest.getProduct() != null){
//                productsInCart.add(addToCartRequest.getProduct());
//                guestCart.setProducts(productsInCart);
//                cartRepository.save(guestCart);
//            }else{
//                guestCart.setProducts(productsInCart);
//                cartRepository.save(guestCart);
//            }
//        }

    }

    @Override
    public void decrementProductCount(IncDecRequest incDecRequest) {
        Cart cart = cartRepository.findCartByHeaderValue(incDecRequest.getHeaderValue());

        if(cart != null){
            for(Product product: cart.getProducts()){
                if(product.getProductId().equals(incDecRequest.getProductId())
                        && incDecRequest.getProductSize().equals(product.getSize())
                ){
                    product.setQuantity(product.getQuantity() - 1);
                    if(product.getQuantity() == 0) {
                        cart.getProducts().remove(product);
                    }
                    cartRepository.save(cart);
                }
            }
        }
    }

    @Override
    public void incrementProductCount(IncDecRequest incDecRequest) {
        Cart cart = cartRepository.findCartByHeaderValue(incDecRequest.getHeaderValue());
        if(cart != null){
            for(Product product: cart.getProducts()){
                if(product.getProductId().equals(incDecRequest.getProductId())
                        && incDecRequest.getProductSize().equals(product.getSize())){
                    product.setQuantity(product.getQuantity() + 1);
                    cartRepository.save(cart);
                }
            }
        }
    }

        public void deleteCart(String cartId){
        Cart doesExist  = cartRepository.findCartByHeaderValue(cartId);
        if (doesExist != null ){
            cartRepository.deleteCartByHeaderValue(cartId);
        }
    }

    public void emptyCart(String headerValue){
        Cart doesExist  = cartRepository.findCartByHeaderValue(headerValue);
        if(doesExist != null){
          doesExist.setProducts(new ArrayList<>());
          cartRepository.save(doesExist);
        }

    }
    public List<Product> getProductsInCart(String headerValue){
        Cart cartLookup = cartRepository.findCartByHeaderValue(headerValue);
        if (cartLookup != null){
            return cartLookup.getProducts();
        }
        return new ArrayList<>();
    }
    public void mergeCarts(Cart guestCart,Cart userCart){
        ArrayList<Product> mergedProducts = new ArrayList<>();
        for(Product guestCartProduct: guestCart.getProducts()){
            boolean found = false;
            for(Product userCartProduct: userCart.getProducts()){
                if(guestCartProduct.getProductId().equals(userCartProduct.getProductId())){
                    userCartProduct.setQuantity(userCartProduct.getQuantity()
                            + guestCartProduct.getQuantity());
                    found = true;
                    break;
                }
            }
            if(!found){
                mergedProducts.add(guestCartProduct);
            }
        }
        cartRepository.delete(guestCart);
        mergedProducts.addAll(userCart.getProducts());
        userCart.setProducts(mergedProducts);
        cartRepository.save(userCart);
    }
//
//    public CartResponse getCartBySessionId(String sessionId){
//        Cart doesExist  = cartRepository.findCartBySessionId(sessionId);
//        if (doesExist != null){
//            return  cartToCartResponse(doesExist);
//        }
//        else {
//            return CartResponse.builder().build();
//        }
//    }
//    public CartResponse getCartByUserId(Long id){
//        Cart doesExist = cartRepository.findCartByUserId(id);
//        if (doesExist != null){
//            return cartToCartResponse(doesExist);
//        }
//        return CartResponse.builder().build();
////        throw new RuntimeException();
//
//    }
//
//
//
//    private CartResponse cartToCartResponse(Cart cart){
//        return CartResponse.builder().
//                cartId(cart.getCartId()).
//                userId(cart.getUserId()).
//                products(cart.getProducts()).build();
//
//    }
//
//    private Product productBuilder(Product p){
//        return Product.builder()
//                .productId(p.getProductId())
////                .picture(p.getPicture())
//                .brand(p.getBrand())
//                .productName(p.getProductName())
//                .description(p.getDescription())
//                .category(p.getCategory())
//                .tags(p.getTags())
//                .size(p.getSize())
//                .unit(p.getUnit())
//                .cost(p.getCost())
//                .currentPrice(p.getCurrentPrice())
//                .isTaxed(p.getIsTaxed())
//                .skuCode(p.getSkuCode())
//                .upcCode(p.getUpcCode())
//                .vendor(p.getVendor())
//                .build();
//
//
//    }

}


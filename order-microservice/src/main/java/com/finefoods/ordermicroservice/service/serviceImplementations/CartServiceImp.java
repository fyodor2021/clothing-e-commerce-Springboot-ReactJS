package com.finefoods.ordermicroservice.service.serviceImplementations;


import com.finefoods.ordermicroservice.dto.AddToCartRequest;
import com.finefoods.ordermicroservice.dto.CartProductDesc;
import com.finefoods.ordermicroservice.dto.ReplaceCartForSignedUserRequest;
import com.finefoods.ordermicroservice.model.Cart;
import com.finefoods.ordermicroservice.repository.CartRepository;
import com.finefoods.ordermicroservice.service.serviceInterfaces.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImp implements CartService {
    final CartRepository cartRepository;
    private final WebClient.Builder webClientBuilder;

    public Cart createCart(String email) {
        Cart cart = Cart.builder().email(email).products(new ArrayList<>()).build();
        return cartRepository.save(cart);

    }
    public void replaceCartForUser(ReplaceCartForSignedUserRequest replaceCartRequest) {
        Cart cart = cartRepository.findCartByEmail(replaceCartRequest.getUserEmail());
        if(cart != null) {
            cart.setProducts(replaceCartRequest.getProducts());
            cartRepository.save(cart);
        }
    }
    public void addToCart(AddToCartRequest addToCartRequest) {
        Cart cart = cartRepository.findCartByEmail(addToCartRequest.getUserEmail());
        if(cart == null) {
           cart = createCart(addToCartRequest.getUserEmail());
        }
        List<CartProductDesc> productsInCart;
        if (cart.getProducts() != null) {
            productsInCart = cart.getProducts();
        } else {
            productsInCart = new ArrayList<>();
        }
        if (addToCartRequest.getProducts() != null) {
            List<CartProductDesc> productsToRemove = new ArrayList<>();
            for (CartProductDesc cartItem : addToCartRequest.getProducts()) {
                if (!productsInCart.isEmpty()) {
                    for (CartProductDesc product : productsInCart) {
                        if (product.getProductId().equals(cartItem.getProductId()) && cartItem.getSize().equals(product.getSize())) {
                            product.setQuantity(product.getQuantity() + cartItem.getQuantity());
                            productsToRemove.add(cartItem);
                            break;
                        }
                    }
                }
            }
            System.out.println();
            addToCartRequest.getProducts().removeAll(productsToRemove);
            if (!addToCartRequest.getProducts().isEmpty()) {
                productsInCart.addAll(addToCartRequest.getProducts());
                cart.setProducts(productsInCart);
                cartRepository.save(cart);
            } else {
                cart.setProducts(productsInCart);
                cartRepository.save(cart);
            }
        }
    }

    public void emptyCart(String headerValue) {
        Cart doesExist = cartRepository.findCartByEmail(headerValue);
        if (doesExist != null) {
            doesExist.setProducts(new ArrayList<>());
            cartRepository.save(doesExist);
        }
    }
}


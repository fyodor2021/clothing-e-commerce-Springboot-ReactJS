package com.finefoods.cartmicroservice.service;
import com.finefoods.cartmicroservice.dto.CartRequest;
import com.finefoods.cartmicroservice.dto.CartResponse;
import com.finefoods.cartmicroservice.model.Cart;
import com.finefoods.cartmicroservice.model.Product;
import com.finefoods.cartmicroservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class CartServiceImp implements CartService {
    final CartRepository cartRepository;


    public String createCartForGuest(){
            Cart cart = Cart.builder()
                    .userId(null)
                    .products(new ArrayList<>()).build();
            cartRepository.save(cart);
        return cart.getCartId();

    }

    public void addToCart(CartRequest request ){
        if (request.getUserId() == null){
            Cart guestCart = cartRepository.findCartByCartId(request.getCartId());
            List<Product> productsInCart = guestCart.getProducts();
            productsInCart.addAll(request.getProducts());
            guestCart.setProducts(productsInCart);
            cartRepository.save(guestCart);
        }
//        if (doesExist == null){
//            Cart cart = Cart.builder()
//                    .userId(request.getUserId())
//                    .products(request.getProducts().stream().map(product -> productBuilder(product)).toList()).build();
//            cartRepository.save(cart);
//        }
        else{
            Cart userCart  = cartRepository.findCartByUserId(request.getUserId());
            List<Product> productsInCart = userCart.getProducts();
            productsInCart.addAll(request.getProducts());
            userCart.setProducts(productsInCart);
            cartRepository.save(userCart);

        }
    }

    public void deleteCart(String cartId){
        Cart doesExist  = cartRepository.findCartByCartId(cartId);
        if (doesExist != null ){
            cartRepository.deleteCartByCartId(cartId);
        }
    }


    public void deleteProductInCart(Long productId, String cartId){
        Cart cartExist = cartRepository.findCartByCartId(cartId);
        List<Product> updatedList = new ArrayList<>();
        if (cartExist != null){
            List<Product> productList = cartExist.getProducts();

            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getProductId().equals(productId)) {
                    iterator.remove();
                    break;
                }
            }
            while (iterator.hasNext()){
                updatedList.add(iterator.next());
            }

            cartExist.setProducts(productList);
            cartRepository.save(cartExist);
        }
    }


    public void deleteAllProductsInCart(String cartId){
        Cart doesExist  = cartRepository.findCartByCartId(cartId);
        if(doesExist != null){
          doesExist.setProducts(new ArrayList<>());
          cartRepository.save(doesExist);
        }

    }
    public List<Product> getProductsInCart(String cartId){
        Cart doesExist  = cartRepository.findCartByCartId(cartId);
        if (doesExist != null){
            return doesExist.getProducts();
        }
        return new ArrayList<>();
    }

    public CartResponse getCartByCartId(String cartId){
        Cart doesExist  = cartRepository.findCartByCartId(cartId);
        if (doesExist != null){
            return  cartToCartResponse(doesExist);
        }
        else {
            return CartResponse.builder().build();

        }


    }
    public CartResponse getCartByUserId(Long id){
        Cart doesExist = cartRepository.findCartByUserId(id);
        if (doesExist != null){
            return cartToCartResponse(doesExist);
        }
        return CartResponse.builder().build();
//        throw new RuntimeException();

    }



    private CartResponse cartToCartResponse(Cart cart){
        return CartResponse.builder().
                cartId(cart.getCartId()).
                userId(cart.getUserId()).
                products(cart.getProducts()).build();

    }

    private Product productBuilder(Product p){
        return Product.builder()
                .productId(p.getProductId())
//                .picture(p.getPicture())
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


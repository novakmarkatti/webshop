package com.webshop.webshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.webshop.webshop.entities.Cart;
import com.webshop.webshop.entities.CartItem;
import com.webshop.webshop.entities.Product;
import com.webshop.webshop.repositories.CartItemRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    private CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CartItem not found with ID: " + id));
    }

    public List<CartItem> getCartItemsByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    public CartItem updateCartItem(Long id, int quantity) {
        CartItem cartItem = getCartItemById(id);
        Optional<Product> optionalProduct = productService.getProductByProductNameAndCategory(cartItem.getProductName(), cartItem.getCategory());
        if(optionalProduct.isPresent() && productService.isThereEnoughProduct(optionalProduct.get().getId(), quantity)) {
            cartItem.setQuantity(quantity);
            cartItem.setPrice(quantity * optionalProduct.get().getPrice());
            return cartItemRepository.save(cartItem);
        }
        return null;
    }

    public CartItem createNewCartItem(Long id, int quantity, Cart cart) {
        if(productService.isThereEnoughProduct(id, quantity)) {
            Product product = productService.getProductById(id);
            CartItem cartItem;
            Optional<CartItem> optionalCartItem = cartItemRepository.findByProductNameAndCategoryAndCart(product.getProductName(), product.getCategory(), cart);
            if(!optionalCartItem.isPresent()) {
                cartItem = new CartItem();
                cartItem.setProductName(product.getProductName());
                cartItem.setCategory(product.getCategory());
                cartItem.setQuantity(quantity);
                cartItem.setPrice(quantity * product.getPrice());
                cartItem.setCart(cart);
            } else {
                cartItem = optionalCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setPrice(cartItem.getQuantity() * product.getPrice());
            }
            return cartItemRepository.save(cartItem);
        }
        return null;
    }

    public CartItem deleteCartItem(Long id) {
        CartItem cartItem = getCartItemById(id);
        cartItemRepository.delete(cartItem);
        return cartItem;
    }

}
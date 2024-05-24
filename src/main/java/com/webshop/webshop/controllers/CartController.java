package com.webshop.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.webshop.webshop.dto.ProductDTO;
import com.webshop.webshop.dto.UserDTO;
import com.webshop.webshop.entities.Cart;
import com.webshop.webshop.entities.CartItem;
import com.webshop.webshop.entities.User;
import com.webshop.webshop.service.CartItemService;
import com.webshop.webshop.service.CartService;
import com.webshop.webshop.service.UserService;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/addProductToCart")
    public ResponseEntity<CartItem> addCartItemToCart(@RequestBody ProductDTO productDTO) {
        if(productDTO.getId() != null && productDTO.getQuantity() > 0) {
            User user = userService.getUser();
            Cart cart = cartService.getOrCreateCartForUser(user.getId());
            CartItem cartItem = cartItemService.createNewCartItem(productDTO.getId(), productDTO.getQuantity(), cart);
            cartService.updateTotal(cart);
            return ResponseEntity.ok().body(cartItem);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateCartItem")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody ProductDTO productDTO) {
        if(productDTO.getId() != null && productDTO.getQuantity() > 0) {
            User user = userService.getUser();
            Cart cart = cartService.getOrCreateCartForUser(user.getId());
            CartItem cartItem = cartItemService.updateCartItem(productDTO.getId(), productDTO.getQuantity());
            if(cartItem != null) {
                cartService.updateTotal(cart);
                return ResponseEntity.ok().body(cartItem);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deleteCartItem/{id}")
    public ResponseEntity<CartItem> deleteCartItem(@PathVariable("id") Long id) {
        CartItem cartItemDeleted = cartItemService.deleteCartItem(id);
        if(cartItemDeleted != null) {
            User user = userService.getUser();
            Cart cart = cartService.getOrCreateCartForUser(user.getId());
            cartService.updateTotal(cart);
            return ResponseEntity.ok().body(cartItemDeleted);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Cart> placeOrder() {
        User user = userService.getUser();
        Cart cart = cartService.getOrCreateCartForUser(user.getId());
        Cart updatedCart = cartService.makeOrder(cart);
        return ResponseEntity.ok().body(updatedCart);
    }

    @PutMapping("/updateUserAddresses")
    public ResponseEntity<User> updateUserAddresses(@RequestBody UserDTO userDTO) {
        User user = userService.updateUserAddresses(userDTO);
        return ResponseEntity.ok().body(user);
    }
}
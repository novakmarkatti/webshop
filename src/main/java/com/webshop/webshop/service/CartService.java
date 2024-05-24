package com.webshop.webshop.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.webshop.webshop.entities.Cart;
import com.webshop.webshop.entities.CartItem;
import com.webshop.webshop.entities.User;
import com.webshop.webshop.repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Integer getNumberOfItemsInCart(Cart cart) {
        return cartItemService.getCartItemsByCart(cart).stream().mapToInt(CartItem::getQuantity).sum();
    }

    public List<CartItem> getShoppingCart(Cart cart) {
        return cartItemService.getCartItemsByCart(cart);
    }

    public void updateTotal(Cart cart) {
        Long result = cartItemService.getCartItemsByCart(cart).stream().mapToLong(CartItem::getPrice).sum();
        cart.setTotal(result);
        cartRepository.save(cart);
    }

    public Cart getOrCreateCartForUser(Long userId) {
        User user = userService.getUserById(userId);
        return cartRepository.findByUserAndOrdered(user, false).orElseGet(() -> createCartForUser(user));
    }

    public Cart makeOrder(Cart cart) {
        cart.setCreatedAt(new Date());
        cart.setOrdered(true);
        cartRepository.save(cart);
        return createCartForUser(cart.getUser());
    }

    private Cart createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotal(0L);
        cart.setOrdered(false);
        return cartRepository.save(cart);
    }

}
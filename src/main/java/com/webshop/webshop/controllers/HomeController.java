package com.webshop.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.webshop.webshop.entities.Cart;
import com.webshop.webshop.entities.User;
import com.webshop.webshop.service.CartService;
import com.webshop.webshop.service.ProductService;
import com.webshop.webshop.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        User user = userService.getUser();
        Cart cart = cartService.getOrCreateCartForUser(user.getId());
        model.addAttribute("cartItemCount", cartService.getNumberOfItemsInCart(cart));
        return "index";
    }

}
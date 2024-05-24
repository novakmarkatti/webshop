package com.webshop.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.webshop.webshop.entities.Cart;
import com.webshop.webshop.entities.User;
import com.webshop.webshop.service.AddressService;
import com.webshop.webshop.service.CartService;
import com.webshop.webshop.service.UserService;

@Controller
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/ShoppingCart")
    public String shoppingCartPage(Model model) {
        addModelParams(model);
        return "ShoppingCart";
    }

    @GetMapping("/BillingAndShipping")
    public String billingAndShippingPage(Model model) {
        addModelParams(model);
        model.addAttribute("addressFields", addressService.getAddressFields());
        return "BillingAndShipping";
    }

    @GetMapping("/OrderAndSummary")
    public String orderAndSummaryPage(Model model) {
        addModelParams(model);
        return "OrderAndSummary";
    }

    private void addModelParams(Model model) {
        User user = userService.getUser();
        model.addAttribute("user", user);
        Cart cart = cartService.getOrCreateCartForUser(user.getId());
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", cartService.getShoppingCart(cart));
        model.addAttribute("cartItemCount", cartService.getNumberOfItemsInCart(cart));
    }
}
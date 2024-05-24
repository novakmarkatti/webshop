package com.webshop.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.webshop.webshop.dto.UserDTO;
import com.webshop.webshop.entities.Cart;
import com.webshop.webshop.entities.User;
import com.webshop.webshop.service.AddressService;
import com.webshop.webshop.service.CartService;
import com.webshop.webshop.service.UserService;

@Controller
public class UserManagerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/UserManager")
    public String userManagerPage(Model model) {
        User user = userService.getUser();
        model.addAttribute("user", user);
        Cart cart = cartService.getOrCreateCartForUser(user.getId());
        model.addAttribute("cartItemCount", cartService.getNumberOfItemsInCart(cart));
        model.addAttribute("addressFields", addressService.getAddressFields());
        return "UserManager";
    }

    @PutMapping("/UserManager/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.updateUser(id, userDTO));
    }

}
package com.webshop.webshop.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webshop.webshop.entities.Cart;
import com.webshop.webshop.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByProductNameAndCategoryAndCart(String productName, String category, Cart cart);
    List<CartItem> findByCart(Cart cart);

}
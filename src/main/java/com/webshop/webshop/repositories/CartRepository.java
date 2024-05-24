package com.webshop.webshop.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webshop.webshop.entities.Cart;
import com.webshop.webshop.entities.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserAndOrdered(User user, boolean ordered);

}
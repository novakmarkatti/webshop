package com.webshop.webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webshop.webshop.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
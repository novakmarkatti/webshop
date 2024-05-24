package com.webshop.webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webshop.webshop.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
package com.webshop.webshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;
    
    @Column(name = "CATEGORY")
    private String category;
    
    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    
    @Column(name = "PRICE")
    private int price;
    
}
package com.webshop.webshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    
    @Column(name = "USERNAME", nullable = false)
    private String username;
    
    @ManyToOne
    @JoinColumn(name = "BILLING_ADDRESS")
    private Address billingAddress;
    
    @ManyToOne
    @JoinColumn(name = "DELIVERY_ADDRESS")
    private Address deliveryAddress;
    
}
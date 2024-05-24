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
@Table(name = "ADDRESS")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;
    
    @Column(name = "CITY", nullable = false)
    private String city;
    
    @Column(name = "STREET", nullable = false)
    private String street;
    
    @Column(name = "HOUSE_NUMBER", nullable = false)
    private int houseNumber;
    
    @Column(name = "STAIRS")
    private String stairs;
    
    @Column(name = "FLAT")
    private String flat;
    
    @Column(name = "DOOR")
    private String door;
    
}
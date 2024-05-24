package com.webshop.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;
    private String zipCode;
    private String city;
    private String street;
    private int houseNumber;
    private String stairs;
    private String flat;
    private String door;

}
package com.webshop.webshop.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.webshop.webshop.dto.AddressDTO;
import com.webshop.webshop.entities.Address;
import com.webshop.webshop.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + id));
    }

    public List<String> getAddressFields() {
        return Arrays.asList("zipCode", "city", "street", "houseNumber", "stairs", "flat", "door");
    }

    public Address updateAddress(AddressDTO addressDTO) {
        Address address = (addressDTO.getId() != null) ? getAddressById(addressDTO.getId()) : new Address();
        address.setZipCode(updateField(address.getZipCode(), addressDTO.getZipCode()));
        address.setCity(updateField(address.getCity(), addressDTO.getCity()));
        address.setStreet(updateField(address.getStreet(), addressDTO.getStreet()));
        address.setHouseNumber(updateField(address.getHouseNumber(), addressDTO.getHouseNumber()));
        address.setStairs(addressDTO.getStairs());
        address.setFlat(addressDTO.getFlat());
        address.setDoor(addressDTO.getDoor());
        return addressRepository.save(address);
    }

    private String updateField(String currentValue, String newValue) {
        return newValue != null && !newValue.isEmpty() ? newValue : currentValue;
    }

    private int updateField(int currentValue, int newValue) {
        return newValue != 0 ? newValue : currentValue;
    }
}
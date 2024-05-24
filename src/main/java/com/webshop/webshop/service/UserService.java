package com.webshop.webshop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.webshop.webshop.dto.AddressDTO;
import com.webshop.webshop.dto.UserDTO;
import com.webshop.webshop.entities.Address;
import com.webshop.webshop.entities.User;
import com.webshop.webshop.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

    public User getUser() {
        return getAllUsers().stream().findFirst().orElseThrow(() -> new EntityNotFoundException("No users found"));
    }
    
    public User updateUserAddresses(UserDTO userDTO) {
        User user = getUserById(userDTO.getId());
        user.setBillingAddress(updateAddressField(user.getBillingAddress(), userDTO.getBillingAddress()));
        user.setDeliveryAddress(updateAddressField(user.getDeliveryAddress(), userDTO.getDeliveryAddress()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User user = getUserById(id);
        user.setFirstName(updateField(user.getFirstName(), userDTO.getFirstName()));
        user.setLastName(updateField(user.getLastName(), userDTO.getLastName()));
        user.setUsername(updateField(user.getUsername(), userDTO.getUsername()));
        user.setBillingAddress(updateAddressField(user.getBillingAddress(), userDTO.getBillingAddress()));
        user.setDeliveryAddress(updateAddressField(user.getDeliveryAddress(), userDTO.getDeliveryAddress()));
        return userRepository.save(user);
    }
    
    private String updateField(String currentValue, String newValue) {
        return newValue != null && !newValue.isEmpty() ? newValue : currentValue;
    }

    private Address updateAddressField(Address currentValue, AddressDTO newValue) {
        return newValue != null && newValue.getId() != null ? addressService.updateAddress(newValue) : currentValue;
    }
}
package com.example.gatewayservice.services;

import com.example.gatewayservice.models.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> getAllUsers();
    User findByUsername(String username);
    User findByID(Long id);
    void delete(long id);
}

package com.example.gatewayservice.services.impl;

import com.example.gatewayservice.models.Role;
import com.example.gatewayservice.models.Status;
import com.example.gatewayservice.models.User;
import com.example.gatewayservice.repositories.RoleRepository;
import com.example.gatewayservice.repositories.UserRepository;
import com.example.gatewayservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final String USER_ROLE_NAME = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        log.info("Register user with username - {}", user.getUsername());
        Role roleUser = roleRepository.findByName(USER_ROLE_NAME);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleUser));
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.debug("Registered user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("In get all - {} users found", users.size());
        return users;
    }

    @Override
    public User findByUsername(String username) {
        log.info("Find user by username - {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByID(Long id) {
        log.info("Find user by ID - {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
        log.info("User by ID - {} successfully deleted", id);
    }
}

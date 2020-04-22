package com.rakeshv.springbootthymeleaf.services;

import com.rakeshv.springbootthymeleaf.models.User;
import com.rakeshv.springbootthymeleaf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserByName(String name) {
        return userRepository.findByNameContains(name);
    }

    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmailContains(email);
    }

    public List<User> getAdultUsers() {
        return userRepository.findByAgeGreaterThanAndAgeLessThan(17, 58);
    }

    public List<User> getMinorUsers() {
        return userRepository.findByAgeGreaterThanAndAgeLessThan(0, 18);
    }

    public List<User> getSeniorUsers() {
        return userRepository.findByAgeGreaterThanAndAgeLessThan(58, 200);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}

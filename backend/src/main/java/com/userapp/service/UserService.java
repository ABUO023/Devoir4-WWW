package com.userapp.service;

import com.userapp.model.User;
import com.userapp.dto.UserDTO;
import com.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User register(String name, String email, String password) {
        // Check if user already exists
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            throw new RuntimeException("User with this email already exists");
        }
        
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // In production, hash this!
        
        return userRepository.save(user);
    }
    
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        
        return user;
    }
    
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
            .collect(Collectors.toList());
    }
}

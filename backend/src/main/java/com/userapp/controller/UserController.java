package com.userapp.controller;

import com.userapp.model.User;
import com.userapp.dto.*;
import com.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.register(request.getName(), request.getEmail(), request.getPassword());
            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());
            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage(), null));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.authenticate(request.getEmail(), request.getPassword());
            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());
            return ResponseEntity.ok(new LoginResponse(true, "Login successful", userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LoginResponse(false, e.getMessage(), null));
        }
    }
    
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage(), null));
        }
    }
}

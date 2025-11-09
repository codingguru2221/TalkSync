package com.TalkSync.TalkSync.controllers;

import com.TalkSync.TalkSync.entities.User;
import com.TalkSync.TalkSync.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        boolean isRegistered = authenticationService.registerUser(
            request.getUsername(), 
            request.getEmail(), 
            request.getPassword()
        );
        
        if (isRegistered) {
            return ResponseEntity.ok(new MessageResponse("User registered successfully"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Username or email already exists"));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request) {
        Optional<User> user = authenticationService.loginUser(
            request.getUsername(), 
            request.getPassword()
        );
        
        if (user.isPresent()) {
            return ResponseEntity.ok(new LoginResponse("Login successful", user.get().getId(), user.get().getUsername()));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid username or password"));
        }
    }
    
    // Request body classes
    static class UserRegistrationRequest {
        private String username;
        private String email;
        private String password;
        
        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    static class UserLoginRequest {
        private String username;
        private String password;
        
        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    static class MessageResponse {
        private String message;
        
        public MessageResponse(String message) {
            this.message = message;
        }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
    
    static class LoginResponse {
        private String message;
        private Long userId;
        private String username;
        
        public LoginResponse(String message, Long userId, String username) {
            this.message = message;
            this.userId = userId;
            this.username = username;
        }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
    }
}
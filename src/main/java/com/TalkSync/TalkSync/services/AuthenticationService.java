package com.TalkSync.TalkSync.services;

import com.TalkSync.TalkSync.entities.User;
import com.TalkSync.TalkSync.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;
    
    public boolean registerUser(String username, String email, String password) {
        // Check if user already exists
        if (userRepository.findByUsername(username).isPresent() || 
            userRepository.findByEmail(email).isPresent()) {
            return false; // User already exists
        }
        
        // Create new user
        User newUser = new User(username, email, password);
        userRepository.save(newUser);
        return true;
    }
    
    public Optional<User> loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
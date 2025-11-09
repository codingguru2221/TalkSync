package com.TalkSync.TalkSync.services;

import com.TalkSync.TalkSync.entities.Contact;
import com.TalkSync.TalkSync.entities.User;
import com.TalkSync.TalkSync.repositories.ContactRepository;
import com.TalkSync.TalkSync.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public boolean addContact(Long userId, String contactUsername) {
        // Find the user
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return false;
        }
        
        User user = userOpt.get();
        
        // Find the contact user
        Optional<User> contactUserOpt = userRepository.findByUsername(contactUsername);
        if (contactUserOpt.isEmpty()) {
            // Contact user doesn't exist, so we can't add them
            return false;
        }
        
        User contactUser = contactUserOpt.get();
        
        // Check if contact already exists
        if (contactRepository.findByUserAndContactUser(user, contactUser).isPresent()) {
            return false; // Contact already exists
        }
        
        // Create new contact
        Contact contact = new Contact(user, contactUser);
        contactRepository.save(contact);
        return true;
    }
    
    public List<Contact> getUserContacts(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return List.of(); // Return empty list if user not found
        }
        
        return contactRepository.findByUser(userOpt.get());
    }
    
    public List<User> getPendingContactRequests(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return List.of(); // Return empty list if user not found
        }
        
        User user = userOpt.get();
        List<Contact> pendingContacts = contactRepository.findByContactUserAndIsAccepted(user, false);
        
        // Extract the users who sent the requests
        return pendingContacts.stream()
                .map(Contact::getUser)
                .toList();
    }
    
    public boolean acceptContactRequest(Long userId, Long requesterId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> requesterOpt = userRepository.findById(requesterId);
        
        if (userOpt.isEmpty() || requesterOpt.isEmpty()) {
            return false;
        }
        
        Optional<Contact> contactOpt = contactRepository.findByUserAndContactUser(requesterOpt.get(), userOpt.get());
        if (contactOpt.isEmpty()) {
            return false;
        }
        
        Contact contact = contactOpt.get();
        contact.setAccepted(true);
        contactRepository.save(contact);
        return true;
    }
}
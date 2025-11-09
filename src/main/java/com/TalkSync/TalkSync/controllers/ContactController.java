package com.TalkSync.TalkSync.controllers;

import com.TalkSync.TalkSync.entities.Contact;
import com.TalkSync.TalkSync.entities.User;
import com.TalkSync.TalkSync.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @PostMapping("/add")
    public ResponseEntity<?> addContact(@RequestParam Long userId, @RequestParam String contactUsername) {
        boolean isAdded = contactService.addContact(userId, contactUsername);
        
        if (isAdded) {
            return ResponseEntity.ok(new MessageResponse("Contact added successfully"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to add contact. User may not exist or contact already exists."));
        }
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Contact>> getUserContacts(@RequestParam Long userId) {
        List<Contact> contacts = contactService.getUserContacts(userId);
        return ResponseEntity.ok(contacts);
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<User>> getPendingContactRequests(@RequestParam Long userId) {
        List<User> pendingRequests = contactService.getPendingContactRequests(userId);
        return ResponseEntity.ok(pendingRequests);
    }
    
    @PostMapping("/accept")
    public ResponseEntity<?> acceptContactRequest(@RequestParam Long userId, @RequestParam Long requesterId) {
        boolean isAccepted = contactService.acceptContactRequest(userId, requesterId);
        
        if (isAccepted) {
            return ResponseEntity.ok(new MessageResponse("Contact request accepted"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to accept contact request"));
        }
    }
    
    // Response classes
    static class MessageResponse {
        private String message;
        
        public MessageResponse(String message) {
            this.message = message;
        }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
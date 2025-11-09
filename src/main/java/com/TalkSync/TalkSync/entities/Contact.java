package com.TalkSync.TalkSync.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "contact_user_id")
    private User contactUser;
    
    private LocalDateTime createdAt;
    
    private boolean isAccepted;
    
    // Constructors
    public Contact() {}
    
    public Contact(User user, User contactUser) {
        this.user = user;
        this.contactUser = contactUser;
        this.createdAt = LocalDateTime.now();
        this.isAccepted = false;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getContactUser() {
        return contactUser;
    }
    
    public void setContactUser(User contactUser) {
        this.contactUser = contactUser;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public boolean isAccepted() {
        return isAccepted;
    }
    
    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
package com.TalkSync.TalkSync.repositories;

import com.TalkSync.TalkSync.entities.Contact;
import com.TalkSync.TalkSync.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUser(User user);
    List<Contact> findByContactUserAndIsAccepted(User contactUser, boolean isAccepted);
    Optional<Contact> findByUserAndContactUser(User user, User contactUser);
}
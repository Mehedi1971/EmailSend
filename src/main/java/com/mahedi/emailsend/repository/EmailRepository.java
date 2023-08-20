package com.mahedi.emailsend.repository;

import com.mahedi.emailsend.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    Email findByVerificationNumber(String verificationNumber);
}

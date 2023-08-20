package com.mahedi.emailsend.service;

import com.mahedi.emailsend.model.Email;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    String sendEmail(MultipartFile[] files, String to, String[] cc, String subject, String body, Email email);

    String getVerify(String verificationNumber);
}

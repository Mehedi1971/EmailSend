package com.mahedi.emailsend.controller;

import com.mahedi.emailsend.model.Email;
import com.mahedi.emailsend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class EmailSendController {

    private final EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestParam MultipartFile[] files, String to, String[] cc, String subject, String body, Email email) {
        return emailService.sendEmail(files,to,cc,subject,body,email);
    }

    @GetMapping("/verificationNumber")
    public String getVerify(@RequestParam String verificationNumber){
        return emailService.getVerify(verificationNumber);
    }

}

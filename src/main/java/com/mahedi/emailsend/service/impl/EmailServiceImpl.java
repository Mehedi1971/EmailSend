package com.mahedi.emailsend.service.impl;

import com.mahedi.emailsend.model.Email;
import com.mahedi.emailsend.repository.EmailRepository;
import com.mahedi.emailsend.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    @Override
    public String sendEmail(MultipartFile[] files,String fromEmail, String to, String[] cc, String subject, String body, Email email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            String verificationNumber = String.valueOf((int) (Math.random() * 1000000));
            if (verificationNumber.length() < 6) {
                verificationNumber = "0" + verificationNumber;
            }
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
//            mimeMessageHelper.setText(body);
            mimeMessageHelper.setText("Your Verification number is " + verificationNumber);
            email.setVerificationNumber(verificationNumber);

            for (int i = 0; i < files.length; i++) {
//                mimeMessageHelper.addAttachment(
//                        files[i].getOriginalFilename(),
//                        new ByteArrayResource(files[i].getBytes())
//                );
                javaMailSender.send(mimeMessage);
            }
            emailRepository.save(email);
            return "Mail Send";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getVerify(String verificationNumber) {
        Email email = emailRepository.findByVerificationNumber(verificationNumber);
        email.setActiveStatus(1);
        emailRepository.save(email);
        return "Your Email Id is Now Verified!!!";
    }
}

package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender mailSender;

    public EmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(EmailDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@ja.com");
        message.setTo(email.toAddress());
        message.setSubject(email.subject());
        message.setText(email.content());
        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Nie udało się wysłać e-maila: " + e.getMessage());
        }
    }
}

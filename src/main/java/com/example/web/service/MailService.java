package com.example.web.service;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {
    public SimpleMailMessage sendMail(String from , String to ,String subject ,String text);
}

package com.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService{
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendMail(String to,String subject,String message){
        SimpleMailMessage smm=new SimpleMailMessage();
        smm.setTo(to);
        smm.setSubject(subject);
        smm.setText(message);
        javaMailSender.send(smm);
    }
}

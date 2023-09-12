package org.berka.service;

import lombok.RequiredArgsConstructor;
import org.berka.rabbitmq.model.MailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {


    private final JavaMailSender javaMailSender;

    public void sendMail(MailModel model){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("${java9mail}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Aktivasyon kodunuz...");
        mailMessage.setText("Code: " + model.getActivationCode());
        javaMailSender.send(mailMessage);
    }
}

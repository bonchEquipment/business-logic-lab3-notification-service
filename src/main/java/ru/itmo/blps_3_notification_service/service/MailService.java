package ru.itmo.blps_3_notification_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.itmo.blps_3_notification_service.dto.SendEmailDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void sendEmailToUser(SendEmailDto message) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(message.getUserEmail());
        mailMessage.setSubject(message.getTheme());
        mailMessage.setText(message.getText());

        javaMailSender.send(mailMessage);
        log.info("successfully send message");
    }

}

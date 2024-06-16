package ru.itmo.blps_3_notification_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmo.blps_3_notification_service.dto.SendEmailDto;
import ru.itmo.blps_3_notification_service.util.ObjectParser;

@SpringBootApplication
public class Blps3NotificationServiceApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(Blps3NotificationServiceApplication.class, args);
    }

}

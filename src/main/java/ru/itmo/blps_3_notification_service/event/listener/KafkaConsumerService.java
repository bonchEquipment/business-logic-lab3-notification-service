package ru.itmo.blps_3_notification_service.event.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.blps_3_notification_service.dto.EmailStatus;
import ru.itmo.blps_3_notification_service.dto.SendEmailDto;
import ru.itmo.blps_3_notification_service.event.producer.KafkaProducerService;
import ru.itmo.blps_3_notification_service.service.MailService;
import ru.itmo.blps_3_notification_service.util.ObjectParser;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final MailService mailService;
    private final KafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "send-email", groupId = "notification-service-group")
    public void listen(String message) throws JsonProcessingException {
        log.info("got message from topic send-email message is {}", message);
        String jsonString = message.substring(1, message.length() - 1).replace("\\\"", "\"").replace("\\\\n", "\\n");
        var dto = ObjectParser.readValue(jsonString, SendEmailDto.class);

        try {
            log.info("converted to dto {}", dto);
            mailService.sendEmailToUser(dto);
            var status = EmailStatus.builder()
                    .status("SUCCESSES")
                    .theme(dto.getTheme())
                    .dateTime(LocalDateTime.now().toString())
                    .userEmail(dto.getUserEmail())
                    .build();
            kafkaProducerService.sendMessage("send-email-result", ObjectParser.parse(status));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            var status = EmailStatus.builder()
                    .status("FAILED")
                    .theme(dto.getTheme())
                    .dateTime(LocalDateTime.now().toString())
                    .userEmail(dto.getUserEmail())
                    .errorMessage(e.getMessage())
                    .build();
            kafkaProducerService.sendMessage("send-email-result", ObjectParser.parse(status));
        }

    }


}


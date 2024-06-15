package ru.itmo.blps_3_notification_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps_3_notification_service.event.producer.KafkaProducerService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ControllerForTest {

    private final KafkaProducerService producerService;


    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody String message){
        log.info("got message {}", message);
        producerService.sendMessage("sasma.test", message);

        return ResponseEntity.ok().build();
    }


}

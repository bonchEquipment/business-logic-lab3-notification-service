package ru.itmo.blps_3_notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SendEmailDto {

    private String text;

    private String userEmail;

    private String theme;

}

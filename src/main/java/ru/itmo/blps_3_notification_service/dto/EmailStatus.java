package ru.itmo.blps_3_notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmailStatus {

    private String status;
    private String theme;
    private String userEmail;
    private String dateTime;
    private String errorMessage;
}

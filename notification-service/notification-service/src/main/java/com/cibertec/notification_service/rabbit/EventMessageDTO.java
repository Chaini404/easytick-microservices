package com.cibertec.notification_service.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventMessageDTO {

    private Long eventId;
    private String titulo;
    private Long organizerId;
    private String token;
}

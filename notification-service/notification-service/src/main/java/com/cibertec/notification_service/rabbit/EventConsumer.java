package com.cibertec.notification_service.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cibertec.notification_service.dto.request.CreateNotificationRequest;
import com.cibertec.notification_service.dto.response.NotificationResponse;
import com.cibertec.notification_service.feign.AuthClient;

import com.cibertec.notification_service.feign.UserResponse;
import com.cibertec.notification_service.model.type.NotificationStatus;
import com.cibertec.notification_service.model.type.NotificationType;
import com.cibertec.notification_service.service.EmailService;
import com.cibertec.notification_service.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final NotificationService notificationService;
    private final EmailService emailService;
    private final AuthClient authClient;
    

    @RabbitListener(queues = RabbitMQConfigNotificacion.QUEUE_NAME)
public void handleEvent(EventMessageDTO message) {


System.out.println("🔑 Token usado: " + message.getToken());  

        // Usa el token que viene en el evento
    UserResponse user = authClient.getUserById(message.getOrganizerId(), message.getToken());

    CreateNotificationRequest request = new CreateNotificationRequest(
            user.getId(),
            NotificationType.EVENT_CREATED,
            "Evento creado",
            "Tu evento '" + message.getTitulo() + "' fue creado correctamente."
    );
System.out.println("📧 Intentando enviar correo a: " + user.getEmail());
    NotificationResponse savedNotification =
            notificationService.createNotification(request);

    try {
        emailService.sendEmail(
                user.getEmail(),
                request.getSubject(),
                request.getMessage()
        );

        notificationService.updateNotificationStatus(
                savedNotification.getId(),
                NotificationStatus.SENT
        );

    } catch (Exception e) {

        notificationService.updateNotificationStatus(
                savedNotification.getId(),
                NotificationStatus.FAILED
        );
    }
}
}
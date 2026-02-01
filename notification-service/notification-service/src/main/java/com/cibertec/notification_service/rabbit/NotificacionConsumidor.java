package com.cibertec.notification_service.rabbit;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cibertec.notification_service.model.UsuarioEvento;
import com.cibertec.notification_service.service.NotificationService;


@Component
public class NotificacionConsumidor {

    private final NotificationService notificacionService;

    public NotificacionConsumidor(NotificationService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @RabbitListener(queues = RabbitMQConfigNotificacion.QUEUE_NAME)
    public void recibirUsuarioEvento(UsuarioEvento usuarioEvento) {
        notificacionService.registrarNotificacion(usuarioEvento);
    }
}

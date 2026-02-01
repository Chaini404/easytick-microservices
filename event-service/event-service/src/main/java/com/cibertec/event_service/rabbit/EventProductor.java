package com.cibertec.event_service.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.cibertec.event_service.model.Event;

@Component
public class EventProductor {

    private final RabbitTemplate rabbitTemplate;

    public EventProductor(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEvento(Event event) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfigEvent.EXCHANGE_NAME,
            RabbitMQConfigEvent.ROUTING_KEY,
            event
        );
    }
}

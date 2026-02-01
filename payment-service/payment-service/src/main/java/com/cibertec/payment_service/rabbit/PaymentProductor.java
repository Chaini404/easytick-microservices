package com.cibertec.payment_service.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.cibertec.payment_service.model.Payment;

@Component
public class PaymentProductor {

    private final RabbitTemplate rabbitTemplate;

    public PaymentProductor(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPayment(Payment payment) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfigPayment.EXCHANGE_NAME,
            RabbitMQConfigPayment.ROUTING_KEY,
            payment
        );

        // envía mensaje a BookingService
rabbitTemplate.convertAndSend(
    RabbitMQConfigBooking.EXCHANGE_NAME,
    RabbitMQConfigBooking.ROUTING_KEY,
    payment
);

// envía mensaje a NotificationService
rabbitTemplate.convertAndSend(
    RabbitMQConfigNotificacion.EXCHANGE_NAME,
    RabbitMQConfigNotificacion.ROUTING_KEY,
    payment
);

// envía mensaje a AnalyticsService
rabbitTemplate.convertAndSend(
    RabbitMQConfigAnalytics.EXCHANGE_NAME,
    RabbitMQConfigAnalytics.ROUTING_KEY,
    payment
);

    }
}

package com.cibertec.payment_service.rabbit;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarConfirmacionPago(Long bookingId, String status) {
        PaymentMessage mensaje = new PaymentMessage(bookingId, status);
        
        rabbitTemplate.convertAndSend(
                RabbitMQConfigPayment.PAYMENT_EXCHANGE, 
                RabbitMQConfigPayment.PAYMENT_ROUTING_KEY, 
                mensaje
        );
        System.out.println("Aviso enviado a Booking: Pago " + status + " para reserva " + bookingId);
    }
}
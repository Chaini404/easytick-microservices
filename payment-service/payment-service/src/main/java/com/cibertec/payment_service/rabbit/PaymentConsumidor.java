package com.cibertec.payment_service.rabbit;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cibertec.payment_service.service.PaymentService;



@Component
public class PaymentConsumidor {

    private final PaymentService paymentService;

    public PaymentConsumidor(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RabbitListener(queues = RabbitMQConfigPayment.QUEUE_NAME)
    public void recibirPago(Payment payment) {
        paymentService.procesarPago(payment);
    }
}

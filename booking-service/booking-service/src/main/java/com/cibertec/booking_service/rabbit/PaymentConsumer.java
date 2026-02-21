package com.cibertec.booking_service.rabbit;

import com.cibertec.booking_service.service.BookingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private final BookingService bookingService;
    private final ObjectMapper objectMapper;

    public PaymentConsumer(BookingService bookingService) {
        this.bookingService = bookingService;
        this.objectMapper = new ObjectMapper(); 
    }

    @RabbitListener(queues = "payment_confirmations_queue") 
    public void receivePaymentConfirmation(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            Long bookingId = jsonNode.get("bookingId").asLong();
            String status = jsonNode.get("status").asText();
            
            System.out.println("✅ BOOKING RECIBIÓ EL MENSAJE: " + status + " para reserva " + bookingId);
            
            bookingService.updateStatusFromPayment(bookingId, status);
            
        } catch (Exception e) {
            System.err.println("❌ Error al procesar confirmación: " + e.getMessage());
        }
    }
}
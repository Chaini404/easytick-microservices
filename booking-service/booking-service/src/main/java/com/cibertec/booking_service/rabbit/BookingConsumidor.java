package com.cibertec.booking_service.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cibertec.booking_service.service.BookingService;


@Component
public class BookingConsumidor {

    private final BookingService bookingService;

    public BookingConsumidor(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RabbitListener(queues = RabbitMQConfigBooking.QUEUE_NAME)
    public void recibirBooking(Booking booking) {
        bookingService.procesarBooking(booking);
    }
}

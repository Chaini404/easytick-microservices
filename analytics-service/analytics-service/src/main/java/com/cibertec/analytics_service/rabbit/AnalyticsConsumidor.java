package com.cibertec.analytics_service.rabbit;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class AnalyticsConsumidor {

    private final AnalyticsService analyticsService;

    public AnalyticsConsumidor(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @RabbitListener(queues = RabbitMQConfigAnalytics.QUEUE_NAME)
    public void recibirBooking(BookingInfo bookingInfo) {
        analyticsService.procesarBooking(bookingInfo);
    }
}

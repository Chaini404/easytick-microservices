package com.cibertec.payment_service.controller;

import com.cibertec.payment_service.dto.request.ConfirmPaymentRequest;
import com.cibertec.payment_service.dto.request.CreatePaymentRequest;
import com.cibertec.payment_service.dto.response.PaymentListResponse;
import com.cibertec.payment_service.dto.response.PaymentResponse;
import com.cibertec.payment_service.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        BigDecimal amount = BigDecimal.valueOf(100); // ejemplo, debería venir del Booking Service
        return ResponseEntity.ok(paymentService.createPayment(request, amount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<PaymentListResponse>> getPaymentsByBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentsByBooking(bookingId));
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<PaymentResponse> confirmPayment(@PathVariable Long id,
                                                          @RequestBody ConfirmPaymentRequest request) {
        return ResponseEntity.ok(paymentService.confirmPayment(id, request));
    }
}

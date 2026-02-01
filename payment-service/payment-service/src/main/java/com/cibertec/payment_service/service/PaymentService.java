package com.cibertec.payment_service.service;

import com.cibertec.payment_service.dto.*;
import com.cibertec.payment_service.dto.request.ConfirmPaymentRequest;
import com.cibertec.payment_service.dto.request.CreatePaymentRequest;
import com.cibertec.payment_service.dto.response.PaymentListResponse;
import com.cibertec.payment_service.dto.response.PaymentResponse;
import com.cibertec.payment_service.mapper.PaymentMapper;
import com.cibertec.payment_service.model.Payment;
import com.cibertec.payment_service.model.type.PaymentStatus;
import com.cibertec.payment_service.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentResponse createPayment(CreatePaymentRequest request, BigDecimal amount) {
        Payment payment = paymentMapper.toEntity(request);
        payment.setAmount(amount); // monto calculado según booking
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setTransactionRef(generateTransactionRef()); // generar referencia única

        Payment saved = paymentRepository.save(payment);
        return paymentMapper.toResponse(saved);
    }

    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return paymentMapper.toResponse(payment);
    }

    public List<PaymentListResponse> getPaymentsByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId)
                .stream()
                .map(paymentMapper::toListResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public PaymentResponse confirmPayment(Long paymentId, ConfirmPaymentRequest request) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        paymentMapper.confirmPayment(request, payment); // MapStruct actualiza solo los campos no nulos
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    private String generateTransactionRef() {
        return "TXN-" + System.currentTimeMillis(); // simple ejemplo
    }
}

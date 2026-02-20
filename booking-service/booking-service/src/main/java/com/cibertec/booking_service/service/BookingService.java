package com.cibertec.booking_service.service;

import com.cibertec.booking_service.model.Booking;
import com.cibertec.booking_service.model.type.BookingStatus;
import com.cibertec.booking_service.repository.BookingRepository;
import com.cibertec.booking_service.dto.*;
import com.cibertec.booking_service.dto.request.CreateBookingRequest;
import com.cibertec.booking_service.dto.request.UpdateBookingStatusRequest;
import com.cibertec.booking_service.dto.response.BookingListResponse;
import com.cibertec.booking_service.dto.response.BookingResponse;
import com.cibertec.booking_service.mapper.BookingMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    
    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request, Long userId, BigDecimal pricePerTicket) {
        Booking booking = bookingMapper.toEntity(request);
        booking.setUserId(userId);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setTotalPrice(pricePerTicket.multiply(BigDecimal.valueOf(request.getQuantity())));
        booking.setCreatedAt(java.time.LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponse(savedBooking);
    }

    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return bookingMapper.toResponse(booking);
    }

    public List<BookingListResponse> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(bookingMapper::toListResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingResponse updateBookingStatus(Long bookingId, UpdateBookingStatusRequest request) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        BookingStatus currentStatus = booking.getBookingStatus();
        BookingStatus newStatus = request.getBookingStatus();

        // 🔴 Regla 1: No se puede modificar si ya está cancelado
        if (currentStatus == BookingStatus.CANCELLED) {
            throw new IllegalStateException("No se puede modificar una reserva cancelada");
        }

        // 🔴 Regla 2: Solo PENDING puede cambiar de estado
        if (currentStatus != BookingStatus.PENDING) {
            throw new IllegalStateException("Solo reservas en estado PENDING pueden cambiar");
        }

        booking.setBookingStatus(newStatus);

        return bookingMapper.toResponse(bookingRepository.save(booking));
    }
    
    @Transactional
    public void procesarBooking(Booking booking) {
        if (booking.getQuantity() == null || booking.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(java.time.LocalDateTime.now());
        bookingRepository.save(booking);
    }
  



}

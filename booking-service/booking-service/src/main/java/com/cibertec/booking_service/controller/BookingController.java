package com.cibertec.booking_service.controller;

import com.cibertec.booking_service.dto.*;
import com.cibertec.booking_service.dto.request.CreateBookingRequest;
import com.cibertec.booking_service.dto.request.UpdateBookingStatusRequest;
import com.cibertec.booking_service.dto.response.BookingListResponse;
import com.cibertec.booking_service.dto.response.BookingResponse;
import com.cibertec.booking_service.service.BookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody CreateBookingRequest request) {
        // Aquí normalmente obtienes userId del token JWT
        Long userId = 1L; // ejemplo
        BigDecimal pricePerTicket = BigDecimal.valueOf(100); // ejemplo
        BookingResponse response = bookingService.createBooking(request, userId, pricePerTicket);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingListResponse>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<BookingResponse> updateBookingStatus(@PathVariable Long id,
                                                               @RequestBody UpdateBookingStatusRequest request) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, request));
    }
}

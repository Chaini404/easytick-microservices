package com.cibertec.event_service.controller;

import com.cibertec.event_service.dto.*;
import com.cibertec.event_service.dto.request.CreateEventRequest;
import com.cibertec.event_service.dto.request.UpdateEventRequest;
import com.cibertec.event_service.dto.response.EventListResponse;
import com.cibertec.event_service.dto.response.EventResponse;
import com.cibertec.event_service.service.EventService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<EventResponse> createEvent(
            @RequestPart("event") CreateEventRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        
        Long organizerId = 1L; 
        return ResponseEntity.ok(eventService.createEvent(request, image, organizerId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping
    public ResponseEntity<List<EventListResponse>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable Long id,
                                                     @RequestBody UpdateEventRequest request) {
        return ResponseEntity.ok(eventService.updateEvent(id, request));
    }
    @PostMapping("/{id}/reduce-slots")
    public ResponseEntity<String> reduceSlots(
            @PathVariable Long id, 
            @RequestParam Integer quantity) {
        
        eventService.reduceAvailableSlots(id, quantity);
        return ResponseEntity.ok("Cupos restados exitosamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Evento eliminado correctamente");
    }

}

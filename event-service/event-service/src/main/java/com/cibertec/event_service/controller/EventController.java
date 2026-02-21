package com.cibertec.event_service.controller;

import com.cibertec.event_service.dto.*;
import com.cibertec.event_service.dto.request.CreateEventRequest;
import com.cibertec.event_service.dto.request.UpdateEventRequest;
import com.cibertec.event_service.dto.response.EventListResponse;
import com.cibertec.event_service.dto.response.EventResponse;
import com.cibertec.event_service.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final ObjectMapper objectMapper; // 👈 inyectado

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EventResponse> createEvent(
            @RequestPart("event") String eventJson,
            @RequestPart(value = "image", required = false) MultipartFile image)
            throws Exception {

        CreateEventRequest request =
                objectMapper.readValue(eventJson, CreateEventRequest.class);

        Long organizerId = 1L;

        return ResponseEntity.ok(
                eventService.createEvent(request, image, organizerId)
        );
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

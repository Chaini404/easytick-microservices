package com.cibertec.event_service.service;


import com.cibertec.event_service.dto.request.CreateEventRequest;
import com.cibertec.event_service.dto.request.UpdateEventRequest;
import com.cibertec.event_service.dto.response.EventListResponse;
import com.cibertec.event_service.dto.response.EventResponse;
import com.cibertec.event_service.mapper.EventMapper;
import com.cibertec.event_service.model.Event;
import com.cibertec.event_service.model.type.EventStatus;
import com.cibertec.event_service.repository.EventRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Transactional
    public EventResponse createEvent(CreateEventRequest request, Long organizerId) {
        Event event = eventMapper.toEntity(request);
        event.setOrganizerId(organizerId);
        event.setEventStatus(EventStatus.ACTIVE); // por defecto activo
        event.setAvailableSlots(request.getCapacity());
        event.setCreatedAt(LocalDateTime.now());

        Event savedEvent = eventRepository.save(event);
        return eventMapper.toResponse(savedEvent);
    }

    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return eventMapper.toResponse(event);
    }

    public List<EventListResponse> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toListResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventResponse updateEvent(Long id, UpdateEventRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // MapStruct actualizará solo los campos no nulos
        eventMapper.updateEntity(request, event);

        Event updatedEvent = eventRepository.save(event);
        return eventMapper.toResponse(updatedEvent);
    }

    @Transactional
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        eventRepository.delete(event);
    }
}

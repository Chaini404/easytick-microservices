package com.cibertec.event_service.service;


import com.cibertec.event_service.cloudinary.CloudinaryService;
import com.cibertec.event_service.dto.request.CreateEventRequest;
import com.cibertec.event_service.dto.request.UpdateEventRequest;
import com.cibertec.event_service.dto.response.EventListResponse;
import com.cibertec.event_service.dto.response.EventResponse;
import com.cibertec.event_service.mapper.EventMapper;
import com.cibertec.event_service.model.Event;
import com.cibertec.event_service.model.EventCategory;
import com.cibertec.event_service.model.type.EventStatus;
import com.cibertec.event_service.rabbit.EventMessageDTO;
import com.cibertec.event_service.rabbit.EventProductor;
import com.cibertec.event_service.repository.EventCategoryRepository;
import com.cibertec.event_service.repository.EventRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CloudinaryService cloudinaryService;
    private final EventCategoryRepository eventCategory;
    private final EventProductor eventProductor;
    
    public List<EventCategory> getAllCategories() {
        return eventCategory.findAll();
    }
    
    public List<EventListResponse> getActiveEvents() {
        return eventRepository.findByEventStatusOrderByIdDesc(EventStatus.ACTIVE)
                .stream()
                .map(eventMapper::toListResponse)
                .collect(Collectors.toList());
    }

    public List<EventListResponse> getEventsByCategory(Long categoryId) {
        return eventRepository.findByCategoryId(categoryId)
                .stream()
                .map(eventMapper::toListResponse)
                .collect(Collectors.toList());
    }
   
    @Transactional
public EventResponse createEvent(CreateEventRequest request,
                                 MultipartFile image,
                                 Long organizerId, String tokenDelUsuario) {

    Event event = eventMapper.toEntity(request);

    if (request.getCategoryId() != null) {
        EventCategory category = eventCategory.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        event.setCategory(category);
    }

    event.setOrganizerId(organizerId);
    event.setEventStatus(EventStatus.ACTIVE);
    event.setAvailableSlots(request.getCapacity());
    event.setCreatedAt(LocalDateTime.now());

    if (image != null && !image.isEmpty()) {
        String imageUrl = cloudinaryService.uploadImage(image);
        event.setImageUrl(imageUrl);
    }

    // 🔥 1. Guardar en BD
    Event savedEvent = eventRepository.save(event);

    // 🔥 2. Crear mensaje para Rabbit
    EventMessageDTO message = new EventMessageDTO(
            savedEvent.getId(),
            savedEvent.getTitle(),   // usa el nombre exacto de tu atributo
            savedEvent.getOrganizerId(),
            tokenDelUsuario 
    );

    // 🔥 3. Publicar evento
    eventProductor.enviarEvento(message);

    // 🔥 4. Retornar respuesta normal
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
        if (request.getCategoryId() != null) {
            EventCategory category = eventCategory.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            event.setCategory(category);
        }

        Event updatedEvent = eventRepository.save(event);
        return eventMapper.toResponse(updatedEvent);
    }
    @Transactional
    public void reduceAvailableSlots(Long id, Integer quantity) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getAvailableSlots() < quantity) {
            throw new IllegalStateException("No hay cupos suficientes para restar");
        }
        event.setAvailableSlots(event.getAvailableSlots() - quantity);
        eventRepository.save(event);
    }
    
    public List<EventListResponse> getEventsByOrganizer(Long organizerId) {
        return eventRepository.findByOrganizerId(organizerId)
                .stream()
                .map(eventMapper::toListResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventResponse changeEventStatus(Long id, EventStatus newStatus) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        event.setEventStatus(newStatus);
        return eventMapper.toResponse(eventRepository.save(event));
    }
    @Transactional
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        eventRepository.delete(event);
    }
}

package com.cibertec.event_service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.cibertec.event_service.dto.request.CreateEventRequest;
import com.cibertec.event_service.dto.request.UpdateEventRequest;
import com.cibertec.event_service.dto.response.EventListResponse;
import com.cibertec.event_service.dto.response.EventResponse;
import com.cibertec.event_service.model.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {

    // Entity → Response
    EventResponse toResponse(Event event);

    // Entity → List Response
    EventListResponse toListResponse(Event event);

    // Request → Entity (create)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizerId", ignore = true)
    @Mapping(target = "availableSlots", ignore = true)
    @Mapping(target = "eventStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Event toEntity(CreateEventRequest request);

    // Request → Entity (UPDATE parcial)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizerId", ignore = true)
    @Mapping(target = "availableSlots", ignore = true)
    @Mapping(target = "eventStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(UpdateEventRequest request, @MappingTarget Event event);
}

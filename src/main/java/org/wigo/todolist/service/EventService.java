package org.wigo.todolist.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.wigo.todolist.dto.ApiResponse;
import org.wigo.todolist.dto.EventRequestDto;
import org.wigo.todolist.dto.EventResponseDto;
import org.wigo.todolist.enums.EventCategory;
import org.wigo.todolist.enums.EventStatus;
import org.wigo.todolist.model.Event;
import org.wigo.todolist.model.User;
import org.wigo.todolist.repository.EventRepository;
import org.wigo.todolist.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    private Event requireOwnedEvent(UUID userId, UUID eventId) {
        return eventRepository.findByUserIdAndId(userId, eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }
    //CRUD

    //Create
    @Transactional
    public EventResponseDto createEvent(UUID userId, EventRequestDto input) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        EventCategory category = null;
        EventStatus status = null;

        try {
            category = EventCategory.valueOf(input.getCategory().toUpperCase());
            status = EventStatus.valueOf(input.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("invalid event category or status");
        }

        Event event = new Event(
            input.getTitle(),
            input.getDescription(),
            input.getLocation(),
            input.getStart(),
            input.getEnd(),
            category,
            status,
                user
        );
        eventRepository.save(event);
        return new EventResponseDto(event);
    }

    //Read
    public List<Event> getEvents(UUID userId) {
        return eventRepository.findAllByUserId(userId);
    }

    //Update
    @Transactional
    public EventResponseDto updateEvent(UUID userId, UUID eventId, EventRequestDto input) {
        //find the event, verify owner, update event
        EventCategory category = EventCategory.valueOf(input.getCategory().toUpperCase());
        EventStatus status = EventStatus.valueOf(input.getStatus().toUpperCase());

        Event currentEvent = requireOwnedEvent(userId, eventId);

        // ownership check — only the creator can edit
        //event.getUser() is getting organiser
//        if (!currentEvent.getUser().getId().equals(userId)) {
//            throw new IllegalStateException("You can only edit your own events");
//        }

        if (input.getTitle() != null) { currentEvent.setTitle(input.getTitle()); }
        if (input.getDescription() != null) { currentEvent.setDescription(input.getDescription()); }
        if (input.getLocation() != null) { currentEvent.setLocation(input.getLocation()); }
        if (input.getStart() != null) { currentEvent.setStart(input.getStart()); }
        if (input.getEnd() != null) { currentEvent.setEnd(input.getEnd()); }
        currentEvent.setCategory(category);
        currentEvent.setStatus(status);
        eventRepository.save(currentEvent);
        return new EventResponseDto(currentEvent);
    }

    //Delete
    @Transactional
    public String deleteEvent(UUID userId, UUID eventId) {
        Event currentEvent = requireOwnedEvent(userId, eventId);
        eventRepository.delete(currentEvent);
        return "Event deleted Successfully";
    }

    //Archive
    @Transactional
    public String archiveEvent(UUID userId, UUID eventId) {
        Event currentEvent = requireOwnedEvent(userId, eventId);
        currentEvent.setActive(false);
        return "Event Archived Successfully";
    }

}

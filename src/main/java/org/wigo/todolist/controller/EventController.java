package org.wigo.todolist.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wigo.todolist.dto.ApiResponse;
import org.wigo.todolist.dto.EventRequestDto;
import org.wigo.todolist.dto.EventResponseDto;
import org.wigo.todolist.model.Event;
import org.wigo.todolist.service.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //CRUD
    //Create
    @PostMapping("/create")
    public ResponseEntity<EventResponseDto> createEvent(@RequestParam UUID userId, @Valid @RequestBody EventRequestDto input) {
        //eventService.createEvent(userId, input);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventService.createEvent(userId, input));
    }

    //GetAll
    @GetMapping("/all")
    public List<Event> getEvents(@RequestParam UUID userId) {
        return eventService.getEvents(userId);
    }

    //Update
    @PatchMapping("/update")
    public ResponseEntity<EventResponseDto> updateEvents(@RequestParam UUID userId,@RequestParam UUID eventId,
                                                         @Valid @RequestBody EventRequestDto input) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventService.updateEvent(userId, eventId, input));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteEvent(@RequestParam UUID userId,
                                                    @RequestParam UUID eventId) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                      .body(new ApiResponse(eventService.deleteEvent(userId, eventId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage()));
        }
    }

    @PostMapping("/archieve")
    public ResponseEntity<ApiResponse> archiveEvent(@RequestParam UUID userId,
                                          @RequestParam UUID eventId) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(eventService.archiveEvent(userId, eventId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage()));
        }
    }

}

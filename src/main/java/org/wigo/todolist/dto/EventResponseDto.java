package org.wigo.todolist.dto;

import org.wigo.todolist.enums.EventCategory;
import org.wigo.todolist.enums.EventStatus;
import org.wigo.todolist.model.Event;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventResponse {
    private String title;
    private String description;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;
    private EventCategory category;
    private EventStatus status;

    public EventResponse(Event event) {
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.start = event.getStart();
        this.end = event.getEnd();
        this.category = event.getCategory();
        this.status = event.getStatus();
    }
}

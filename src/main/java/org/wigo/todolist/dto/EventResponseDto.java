package org.wigo.todolist.dto;

import lombok.Getter;
import lombok.Setter;
import org.wigo.todolist.enums.EventCategory;
import org.wigo.todolist.enums.EventStatus;
import org.wigo.todolist.model.Event;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EventResponseDto {
    private UUID id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;
    private EventCategory category;
    private EventStatus status;

    public EventResponseDto(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.start = event.getStart();
        this.end = event.getEnd();
        this.category = event.getCategory();
        this.status = event.getStatus();
    }
}

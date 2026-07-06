package org.wigo.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wigo.todolist.enums.EventCategory;
import org.wigo.todolist.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    private String location;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime start;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private boolean active = true;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Event(String title,
                 String description,
                 String location,
                 LocalDateTime start,
                 LocalDateTime end,
                 EventCategory category,
                 EventStatus status,
                 User user) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.category = category;
        this.status = status;
        this.user = user;
    }
}

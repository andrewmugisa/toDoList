package org.wigo.todolist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class EventRequestDto {
    @NotNull (message = "Title cant be blank")
    private String title;

    private String description;

    private String location;

    @NotNull (message = "Start date and time cant be blank")
    private LocalDateTime start;

    @NotNull (message = "End date and time cant be blank")
    private LocalDateTime end;

    @NotNull (message = "Category cant be blank")
    private String category;

    @NotNull (message = "Status cant be blank")
    private String status;

}

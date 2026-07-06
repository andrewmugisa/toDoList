package org.wigo.todolist.dto;

import lombok.Getter;
import org.wigo.todolist.model.User;

import java.util.UUID;

@Getter
public class UserResponseDto {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String name;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.name = user.getName();
    }

}

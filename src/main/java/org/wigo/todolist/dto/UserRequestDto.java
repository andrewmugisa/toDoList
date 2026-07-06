package org.wigo.todolist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotNull (message = "Username cant be blank")
    private String username;

    @NotNull(message = "Password cant be blank")
    private String password;

    @NotNull (message = "Email cant be blank")
    private String email;

    @NotNull (message = "Name cant be blank")
    private String name;
}

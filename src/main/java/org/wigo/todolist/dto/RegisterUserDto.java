package org.wigo.todolist.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank (message = "Username cant be blank")
    private String username;

    @NotBlank(message = "Password cant be blank")
    private String password;

    @NotBlank (message = "Email cant be blank")
    private String email;

    @NotBlank(message = "Name cant be blank")
    private String name;
}

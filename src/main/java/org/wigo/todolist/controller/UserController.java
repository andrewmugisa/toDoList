package org.wigo.todolist.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wigo.todolist.dto.ApiResponse;
import org.wigo.todolist.dto.AuthenticateUserDto;
import org.wigo.todolist.dto.RegisterUserDto;
import org.wigo.todolist.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/u")
//@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateUserDto authenticateUserDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.login(authenticateUserDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage()));
        }
    }

    //register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userService.createUser(registerUserDto));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam UUID userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(userService.deleteUser(userId)));
    }
}

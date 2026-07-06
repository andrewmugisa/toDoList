package org.wigo.todolist.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wigo.todolist.dto.ApiResponse;
import org.wigo.todolist.dto.LoginRequestDto;
import org.wigo.todolist.dto.UserRequestDto;
import org.wigo.todolist.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.login(loginRequestDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage()));
        }
    }

    //register
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequestDto userRequestDto) {
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userService.createUser(userRequestDto));

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

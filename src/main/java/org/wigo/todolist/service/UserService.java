package org.wigo.todolist.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wigo.todolist.dto.ApiResponse;
import org.wigo.todolist.dto.LoginRequestDto;
import org.wigo.todolist.dto.UserRequestDto;
import org.wigo.todolist.dto.UserResponseDto;
import org.wigo.todolist.model.User;
import org.wigo.todolist.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //Login
    //Login
    public UserResponseDto login(LoginRequestDto input) {
        User user = userRepository.findByUsername(input.getUsername())
                .filter(u -> u.getPassword().equals(input.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        return new UserResponseDto(user);
    }


    //CRUD
    @Transactional
    public UserResponseDto createUser(UserRequestDto input) {
        if(userRepository.existsByUsername(input.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User(
                input.getUsername(),
                input.getPassword(),
                input.getEmail(),
                input.getName()
        );
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    //Read
    public User getUserDetails(User  user) {
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    //Update
    public User updateUser(User user, UserRequestDto input) {
        //find and update
       User currentUser =  userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (input.getUsername() != null &&
                !input.getUsername().equals(currentUser.getUsername())) {

            boolean usernameExists = userRepository.existsByUsername(input.getUsername());

            if (usernameExists) {
                throw new IllegalArgumentException("Username already taken");
            }

            currentUser.setUsername(input.getUsername());
        }
       if (input.getEmail() != null) { currentUser.setEmail(input.getEmail()); }
       if (input.getName() != null) { currentUser.setName(input.getName()); }
       if (input.getPassword() != null) { currentUser.setPassword(input.getPassword()); }
        return userRepository.save(currentUser);
    }

    //Delete
    @Transactional
    public String deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
        return "Deleted";
    }

}

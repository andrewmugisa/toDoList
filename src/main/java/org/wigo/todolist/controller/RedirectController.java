package org.wigo.todolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/")
public class RedirectController {
    @GetMapping()
    public ResponseEntity<Void> redirectToIndex() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(URI.create("/index.html"))
                .build();
    }

}

package com.my.api.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @GetMapping("/{userId}/token")
    public ResponseEntity<Void> getToken(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().build();
    }
}

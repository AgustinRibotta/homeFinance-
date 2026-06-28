package com.homeFinance.homeFinance.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.homeFinance.homeFinance.dto.UserRequest;
import com.homeFinance.homeFinance.dto.UserResponse;
import com.homeFinance.homeFinance.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "Managmen of the user")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Find user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.findById(id));
    }
    
    @Operation(summary = "Create new user")
    @PostMapping
    public ResponseEntity<UserResponse> post (@Validated @RequestBody UserRequest request){
        UserResponse response = userService.create(request);
        URI location = URI.create("/api/v1/users/" + response.id());
        return ResponseEntity.created(location).body(response);
            
    }

}

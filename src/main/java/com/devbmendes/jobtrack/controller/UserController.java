package com.devbmendes.jobtrack.controller;

import com.devbmendes.jobtrack.dto.ApiResponse;
import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService= userService;
    }

    @PostMapping ("/")
    public ResponseEntity<ApiResponse<User>>saveUser(@Valid @RequestBody User user){
        User userCreated = userService.create(user);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("User Created",userCreated));
    }


}

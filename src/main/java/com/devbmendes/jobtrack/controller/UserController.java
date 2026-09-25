package com.devbmendes.jobtrack.controller;

import com.devbmendes.jobtrack.dto.ApiResponse;
import com.devbmendes.jobtrack.dto.UserRequest;
import com.devbmendes.jobtrack.dto.UserResponse;
import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService= userService;
    }

    @PostMapping ("/")
    public ResponseEntity<ApiResponse<UserResponse>>saveUser(@Valid @RequestBody UserRequest user){
        UserResponse userCreated =
                userService.create(user);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("User Created",userCreated));
    }
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<UserResponse>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("All users",userService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("User with id : "+id,userService.findById(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("User deleted",null));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateUser(
            @PathVariable Long id,@RequestBody UserRequest user){
        UserResponse userUpdated = userService.update(id,user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("User updated",null));

    }


}

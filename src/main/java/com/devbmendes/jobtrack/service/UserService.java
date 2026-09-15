package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.UserRequest;
import com.devbmendes.jobtrack.dto.UserResponse;
import com.devbmendes.jobtrack.entity.User;


import java.util.List;

public interface UserService {
     UserResponse create(UserRequest user);

     List<UserResponse> findAll();
     UserResponse findById(Long id);
     UserResponse update(Long id, User user);
     void delete(Long id);

}

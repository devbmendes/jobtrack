package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.repository.UserRepository;

public interface UserService {
    public User create(User user);

}

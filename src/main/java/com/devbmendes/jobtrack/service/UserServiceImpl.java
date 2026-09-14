package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}

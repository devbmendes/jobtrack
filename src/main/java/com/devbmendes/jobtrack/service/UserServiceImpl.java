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
    
    public UserRepository getUserRepository(){
        return userRepository;
    }

    @Override
    public User creat(User user) {
        return null;
    }
}

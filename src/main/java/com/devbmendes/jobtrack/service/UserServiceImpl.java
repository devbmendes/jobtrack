package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.repository.UserRepository;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public UserServiceImpl(){

    }

    public void setUserRepository(UserRepository userRepository) {
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

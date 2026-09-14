package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.exceptions.EmailAlreadyExistsException;
import com.devbmendes.jobtrack.exceptions.ResourceNotFoundException;
import com.devbmendes.jobtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            return userRepository.save(user);
        }
        throw new
                EmailAlreadyExistsException("User with  email: "+user.getEmail()+" already exists");

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException("User not found with id : "+id);
        }
        return userOptional.get();
    }

    @Override
    public User update(Long id, User user) {
        findById(id);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
            userRepository.deleteById(id);
    }
}

package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.UserRequest;
import com.devbmendes.jobtrack.dto.UserResponse;
import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.enums.Role;
import com.devbmendes.jobtrack.exceptions.EmailAlreadyExistsException;
import com.devbmendes.jobtrack.exceptions.ResourceNotFoundException;
import com.devbmendes.jobtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private UserResponse convertUser(User user){

        return new UserResponse(user.getName(),user.getEmail(),user.getCreatedAt());
    }
    @Override
    public UserResponse create(UserRequest user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            User saveUser = new User();
            saveUser.setName(user.getName());
            saveUser.setEmail(user.getEmail());
            saveUser.setPassword(user.getPassword());
            saveUser.setCreatedAt(LocalDateTime.now());
            if(user.getRole().equalsIgnoreCase("admin")){
                saveUser.setRole(Role.ADMIN);
            }else {
                saveUser.setRole(Role.USER);
            }
            return convertUser(userRepository.save(saveUser));
        }
        throw new
                EmailAlreadyExistsException("User with  email: "+user.getEmail()+" already exists");

    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        for (User user : users) {
            UserResponse userResponse = new UserResponse(
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt()
            );
            userResponseList.add(userResponse);
        }

        return userResponseList;
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException("User not found with id : "+id);
        }
        return new UserResponse(userOptional.get().getName(),userOptional.get().getEmail()
        ,userOptional.get().getCreatedAt());
    }

    @Override
    public UserResponse update(Long id, User user) {

        Optional<User> userById = userRepository.findById(id);

        Optional<User> userByEmail =
                userRepository.findByEmail(user.getEmail());

        if (userByEmail.isPresent() &&
                !userByEmail.get().getId().equals(id)) {

            throw new EmailAlreadyExistsException(
                    "User with email : " + user.getEmail() + " already exist"
            );
        }

        userById.get().setName(user.getName());
        userById.get().setEmail(user.getEmail());
        userById.get().setPassword(user.getPassword());

        User save = userRepository.save(userById.get());
        return new UserResponse(save.getName(),save.getEmail(),save.getCreatedAt());
    }

    @Override
    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}

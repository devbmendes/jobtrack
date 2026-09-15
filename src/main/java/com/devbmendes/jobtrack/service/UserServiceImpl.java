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

        User userById = findById(id);

        Optional<User> userByEmail =
                userRepository.findByEmail(user.getEmail());

        if (userByEmail.isPresent() &&
                !userByEmail.get().getId().equals(id)) {

            throw new EmailAlreadyExistsException(
                    "User with email : " + user.getEmail() + " already exist"
            );
        }

        userById.setName(user.getName());
        userById.setEmail(user.getEmail());
        userById.setPassword(user.getPassword());

        return userRepository.save(userById);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}

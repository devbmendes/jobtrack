package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserService {
     User create(User user);

     List<User> findAll();
     Optional<User> findById(Long id);
     User update(Long id, User user);
     void delete(Long id);

}

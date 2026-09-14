package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.entity.User;


import java.util.List;

public interface UserService {
     User create(User user);

     List<User> findAll();
     User findById(Long id);
     User update(Long id, User user);
     void delete(Long id);

}

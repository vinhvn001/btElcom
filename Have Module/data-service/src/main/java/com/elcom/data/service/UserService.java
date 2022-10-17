package com.elcom.data.service;


import com.elcom.data.model.library.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    void save(User user);

    void remove(User user);
}

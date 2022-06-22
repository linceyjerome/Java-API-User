package com.FirstSpringApp.demo.service;

import com.FirstSpringApp.demo.model.User;

public interface UserService {
    void updateUser(User user);
    User addUser(User user);
    User getUserById(Long userId);
}

package com.FirstSpringApp.demo.service;

import com.FirstSpringApp.demo.model.User;
import com.FirstSpringApp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void  updateUser(User user) {
        User userInDb = userRepository.findById(user.getId()).orElseThrow();
        userRepository.save(user);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }
}

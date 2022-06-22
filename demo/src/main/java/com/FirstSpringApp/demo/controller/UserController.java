package com.FirstSpringApp.demo.controller;

import com.FirstSpringApp.demo.ErrorHandling.CustomErrorType;
import com.FirstSpringApp.demo.model.User;
import com.FirstSpringApp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@RequestBody User user) {
        if (user.getPhone().matches("(?:\\d{3}-){2}\\d{4}")
                && user.getEmail().matches("^(.+)@(.+)$")
                && user.getName().matches("^[a-zA-Z ]+$"))
        {
            return userService.addUser(user);
        }else
            return new ResponseEntity<>(new CustomErrorType("Unable to create A User try Phone : 111-111-1111 Email : email@email.com and name only Strings"),
                HttpStatus.CONFLICT);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> userData = Optional.ofNullable(userService.getUserById(id));

        if (userData.isPresent()) {
            User userEdited = userData.get();
            userEdited.setName(user.getName());
            userEdited.setEmail(user.getEmail());
            userEdited.setPhone(user.getPhone());
            userEdited.setCredit(user.getCredit());
            userService.updateUser(userEdited);
            return new ResponseEntity<>(userEdited,HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateCredit(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> userData = Optional.ofNullable(userService.getUserById(id));
        if (userData.isPresent()) {
            User userEditCrediet = userData.get();
            userEditCrediet.setCredit(user.getCredit());
            return new ResponseEntity<>(userEditCrediet,HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

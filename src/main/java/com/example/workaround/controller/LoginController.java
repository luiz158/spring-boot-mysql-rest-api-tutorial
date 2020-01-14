package com.example.workaround.controller;

import com.example.workaround.model.User;
import com.example.workaround.repository.UserRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

  @Autowired
  UserRepository userRepository;


  @PostMapping("/login")
  public User loginUser(@Valid @RequestBody User user) {
    User u = userRepository.findById(user.getId()).get();
    if (u.getPassword().equals(user.getPassword()))
      return user;
    else {
      throw new RuntimeException("Invalid User !!");
    }
  }

}

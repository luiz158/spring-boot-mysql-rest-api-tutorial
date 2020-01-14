package com.example.easynotes.controller;

import com.example.easynotes.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

  // @Autowired
  // UserRepository userRepository;


  @PostMapping("/login")
  public User createNote(User user) {
    return user;
  }

}

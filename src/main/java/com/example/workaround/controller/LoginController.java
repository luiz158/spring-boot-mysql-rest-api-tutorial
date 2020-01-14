package com.example.workaround.controller;

import com.example.workaround.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

  // @Autowired
  // UserRepository userRepository;


  @PostMapping("/login")
  public User loginUser(User user) {
    return user;
  }

}

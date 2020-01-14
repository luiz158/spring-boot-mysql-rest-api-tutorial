package com.example.workaround;

import com.example.workaround.model.User;
import com.example.workaround.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WorkaroundApplication {


  public static void main(String[] args) {


    SpringApplication.run(WorkaroundApplication.class, args);
  }

  @Bean
  public CommandLineRunner insertDemoData(UserRepository userRepository) {
    return args -> {
      User user1 = new User(new Long(1), "admin");
      userRepository.save(user1);
    };
  }
}

package com.example.workaround;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WorkaroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkaroundApplication.class, args);
	}
}

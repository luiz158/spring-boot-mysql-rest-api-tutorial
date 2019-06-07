package com.controle.epi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ControleEpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleEpiApplication.class, args);
	}
}

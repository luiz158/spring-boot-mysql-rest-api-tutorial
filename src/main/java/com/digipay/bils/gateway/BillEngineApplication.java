package com.digipay.bils.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BillEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillEngineApplication.class, args);
	}
}

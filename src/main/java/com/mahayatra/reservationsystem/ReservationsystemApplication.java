package com.mahayatra.reservationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.mahayatra")
@EnableJpaRepositories(basePackages = "com.mahayatra.repository")
@EntityScan(basePackages = "com.mahayatra.model")

public class ReservationsystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReservationsystemApplication.class, args);
	}


}

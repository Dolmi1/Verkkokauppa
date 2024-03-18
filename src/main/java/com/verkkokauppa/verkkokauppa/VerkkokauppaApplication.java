package com.verkkokauppa.verkkokauppa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.verkkokauppa.verkkokauppa")
@EntityScan(basePackages = "com.verkkokauppa.verkkokauppa")
public class VerkkokauppaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerkkokauppaApplication.class, args);
	}

}

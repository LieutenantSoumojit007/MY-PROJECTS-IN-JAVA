package com.example.MyProject10;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MyProject10Application {

	public static void main(String[] args) {
		SpringApplication.run(MyProject10Application.class, args);
	}
//	 @Bean
//	    CommandLineRunner runner(PasswordEncoder passwordEncoder) {
//
//	        return args -> {
//
//	            System.out.println(passwordEncoder.encode("admin123"));
//
//	        };
//	    }
//
}

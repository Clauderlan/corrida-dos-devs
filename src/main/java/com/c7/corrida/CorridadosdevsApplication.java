package com.c7.corrida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CorridadosdevsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorridadosdevsApplication.class, args);
	}

	@RestController()
	class HttpHome{
		@GetMapping("/")
		String home(){
			return "<h1> LOGADO </h1>";
		}
	}

}

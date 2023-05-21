package com.c7.corrida;

import com.c7.corrida.entities.User;
import com.c7.corrida.entities.auxiliary.AuxiliaryLogin;
import com.c7.corrida.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CorridadosdevsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorridadosdevsApplication.class, args);
	}

	@RestController
	class HttpHome{

		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		private TokenService tokenService;

		@GetMapping("/")
		String home(){
			return "<h1> TELA PRINCIPAL </h1>";
		}

		@GetMapping("/login")
		String loginGet(){
			return "VASCO PORRA ";
		}
		@PostMapping("/login")
		String login(@RequestBody AuxiliaryLogin auxiliaryLogin){
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken
							(auxiliaryLogin.getUsername(), auxiliaryLogin.getPassword());
			Authentication authenticate = this.authenticationManager.authenticate(authenticationToken);
			User user = (User) authenticate.getPrincipal();
			return tokenService.generatorToken(user);
		}
	}
}

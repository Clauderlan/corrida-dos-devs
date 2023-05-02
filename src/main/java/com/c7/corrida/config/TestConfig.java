package com.c7.corrida.config;

import com.c7.corrida.entities.User;
import com.c7.corrida.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {


        userRepository.save(new User(null,"Claudior", "9999","claudior@gmail.com",20,"VASCO"));

    }
}



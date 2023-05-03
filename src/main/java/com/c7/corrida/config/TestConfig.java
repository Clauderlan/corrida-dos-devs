package com.c7.corrida.config;

import com.c7.corrida.entities.Category;
import com.c7.corrida.entities.Challenge;
import com.c7.corrida.entities.Material;
import com.c7.corrida.entities.User;
import com.c7.corrida.repositories.CategoryRepository;
import com.c7.corrida.repositories.ChallengeRepository;
import com.c7.corrida.repositories.MaterialRepository;
import com.c7.corrida.repositories.UserRepository;
import com.c7.corrida.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User(null,"Claudior", "9999","claudior@gmail.com",20,"VASCO"));

        Material m1 = new Material(null, "VascoCourse","","","","");
        Material m2 = new Material(null, "VascoCourse2","","","","");
        Material m3 = new Material(null, "VascoCourse3","","","","");

        materialRepository.save(m1);
        materialRepository.save(m2);
        materialRepository.save(m3);

        Challenge c1 = new Challenge(null, "VASCO","","", Instant.now(),"",20);
        challengeRepository.save(c1);

        Category cc1 = new Category(null, 1, "Admin");
        categoryRepository.save(cc1);
    }
}



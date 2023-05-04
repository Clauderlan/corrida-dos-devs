package com.c7.corrida.config;

import com.c7.corrida.entities.*;
import com.c7.corrida.entities.contents.*;
import com.c7.corrida.entities.enums.*;
import com.c7.corrida.repositories.*;
import com.c7.corrida.repositories.contents.*;
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
    @Autowired
    private MaterialContentRepository materialContentRepository;
    @Autowired
    private ChallengeContentRepository challengeContentRepository;
    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User(null,"Claudior", "9999","claudior@gmail.com",20,"VASCO"));

        Material m1 = new Material(null, "VascoCourse","","","","");
        Material m2 = new Material(null, "VascoCourse2","","","","");
        Material m3 = new Material(null, "VascoCourse3","","","","");

        materialRepository.save(m2);
        materialRepository.save(m3);

        Challenge c1 = new Challenge(null, "VASCO","","", Instant.now(),"",20);

        ChallengeContent ccontent1 = new ChallengeContent(null, "VASCO DA GAMA");
        c1.getChallengeContent().add(ccontent1);
        challengeContentRepository.save(ccontent1);
        challengeRepository.save(c1);

        Category cc1 = new Category(null, CategoryRule.ADMIN, "Admin");
        categoryRepository.save(cc1);

        MaterialContent mc1 = new MaterialContent(null, "VASCO DA GAMA");
        MaterialContent mc2 = new MaterialContent(null, "VASCO DA GAMA2");
        MaterialContent mc3 = new MaterialContent(null, "VASCO DA GAMA3");
        materialContentRepository.save(mc1);
        materialContentRepository.save(mc2);
        materialContentRepository.save(mc3);
        m1.getMaterialContent().add(mc1);
        m1.getMaterialContent().add(mc2);
        m1.getMaterialContent().add(mc3);

        materialRepository.save(m1);

    }
}



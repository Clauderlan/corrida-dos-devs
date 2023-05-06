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
    @Autowired
    private SocialNetworkRepository socialNetworkRepository;
    @Autowired
    private ChallengeResponseRepository challengeResponseRepository;
    @Override
    public void run(String... args) throws Exception {

        Category cc1 = new Category(null, CategoryRule.ADMIN);
        categoryRepository.save(cc1);

        User user1 = new User(null,"Claudior", "9999","claudior@gmail.com",20,"VASCO");
        userRepository.save(user1);
        user1.setCategory(cc1);
        cc1.getUsers().add(user1);

        User user2 = new User(null,"FLU", "9999","claudior@gmail.com",20,"VASCO");
        user2.setCategory(cc1);
        userRepository.save(user2);
        cc1.getUsers().add(user2);


        userRepository.save(user1);
        userRepository.save(user2);
        categoryRepository.save(cc1);

        SocialNetwork sn1 = new SocialNetwork(null, "@VASCO");
        SocialNetwork sn2 = new SocialNetwork(null, "@FLU");
        socialNetworkRepository.save(sn1);
        socialNetworkRepository.save(sn2);
        user1.getSocialNetworks().add(sn1);
        user1.getSocialNetworks().add(sn2);
        userRepository.save(user1);

        Challenge c1 = new Challenge(null, "VASCO","","", Instant.now(),"",20);
        ChallengeContent ccontent1 = new ChallengeContent(null, "VASCO DA GAMA");
        c1.getChallengeContent().add(ccontent1);
        challengeContentRepository.save(ccontent1);
        challengeRepository.save(c1);

        ChallengeResponse cr1 = new ChallengeResponse(user1,c1,"VaSCO.COM",false);
        challengeResponseRepository.save(cr1);
        user1.getChallengeResponse().add(cr1);
        userRepository.save(user1);

        Material m1 = new Material(null, "VascoCourse","","","","");
        Material m2 = new Material(null, "VascoCourse2","","","","");
        Material m3 = new Material(null, "VascoCourse3","","","","");
        materialRepository.save(m2);
        materialRepository.save(m3);

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



package com.c7.corrida.services;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.repositories.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public List<Challenge> findAll(){
        return challengeRepository.findAll();
    }

    public Challenge insert(Challenge challenge){
        return challengeRepository.save(challenge);
    }
}

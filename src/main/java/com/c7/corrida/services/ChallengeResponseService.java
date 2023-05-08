package com.c7.corrida.services;

import com.c7.corrida.entities.ChallengeResponse;
import com.c7.corrida.repositories.ChallengeResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeResponseService {
    @Autowired
    ChallengeResponseRepository challengeResponseRepository;


    public List<ChallengeResponse> findAll(){
        return challengeResponseRepository.findAll();
    }
    public ChallengeResponse insert(ChallengeResponse challengeResponse){
        return challengeResponseRepository.save(challengeResponse);
    }
}

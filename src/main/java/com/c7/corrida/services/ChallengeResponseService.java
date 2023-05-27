package com.c7.corrida.services;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.entities.ChallengeResponse;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.auxiliary.AuxiliaryChallengeResponse;
import com.c7.corrida.repositories.ChallengeRepository;
import com.c7.corrida.repositories.ChallengeResponseRepository;
import com.c7.corrida.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeResponseService {
    @Autowired
    private ChallengeResponseRepository challengeResponseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    public List<ChallengeResponse> findAll(){
        return challengeResponseRepository.findAll();
    }
    public ChallengeResponse insert(AuxiliaryChallengeResponse auxiliaryChallengeResponse){
        User user = auxiliaryChallengeResponse.getUser();
        Challenge challenge = auxiliaryChallengeResponse.getChallenge();
        String responseLink = auxiliaryChallengeResponse.getResponseLink();

        ChallengeResponse challengeResponse = new ChallengeResponse(user, challenge, responseLink);
        challengeResponse = challengeResponseRepository.save(challengeResponse);

        challenge.getChallengeResponse().add(challengeResponse);
        challengeRepository.save(challenge);

        user.getChallengeResponse().add(challengeResponse);
        userRepository.save(user);

        return challengeResponse;
    }
}

package com.c7.corrida.services;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.entities.ChallengeResponse;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.auxiliary.AuxiliaryChallengeResponse;
import com.c7.corrida.entities.contents.ChallengeContent;
import com.c7.corrida.repositories.ChallengeRepository;
import com.c7.corrida.repositories.ChallengeResponseRepository;
import com.c7.corrida.repositories.UserRepository;
import com.c7.corrida.repositories.contents.ChallengeContentRepository;
import com.c7.corrida.services.exceptions.DTOException;
import com.c7.corrida.services.exceptions.DatabaseException;
import com.c7.corrida.services.exceptions.ResourceExistsException;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ChallengeContentRepository challengeContentRepository;
    @Autowired
    private ChallengeResponseRepository challengeResponseRepository;
    @Autowired
    private UserRepository userRepository;

    public void alreadyExists(Challenge challenge){
        if(challengeRepository.existsByTitle(challenge.getTitle())){
            throw new ResourceExistsException(challenge.getTitle());
        }
    }

    public List<Challenge> findAll(){
        return challengeRepository.findAll();
    }
    public Challenge findById(Long id){ return challengeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));}

    public Challenge insert(Challenge challenge){
        alreadyExists(challenge);
        if(challenge.getChallengeContent().size() > 0){
            for(ChallengeContent x : challenge.getChallengeContent()){
                challengeContentRepository.save(x);
            }
        }
        return challengeRepository.save(challenge);
    }
    public Challenge update(Long id,Challenge challenge){
        alreadyExists(challenge);
        Challenge challengeCompare = findById(id);
        for(ChallengeContent x : challenge.getChallengeContent()){
            challengeContentRepository.save(x);
        }
        updateData(challenge, challengeCompare);
        challengeRepository.save(challengeCompare);
        return challengeCompare;
    }

    private void updateData(Challenge challenge, Challenge challengeCompare) {
        challengeCompare.setTitle(challenge.getTitle());
        challengeCompare.setBio(challenge.getBio());
        challengeCompare.setUrlImage(challenge.getUrlImage());
        challengeCompare.setDeadline(challenge.getDeadline());
        challengeCompare.setPoints(challenge.getPoints());
        challengeCompare.getChallengeContent().addAll(challenge.getChallengeContent());

    }
    public void delete(Long id){
        try{
            challengeRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    // Challenge Content routes

    public List<ChallengeContent> findByIdContent(Long id){
        List<ChallengeContent> challengeContents = challengeContentRepository.findByChallenge(id);
        if(challengeContents.size() == 0){
            throw new ResourceNotFoundException(id);
        }
        return challengeContents;
    }

    // Challenge Response routes

    public List<ChallengeResponse> findAllResponse(){
        return challengeResponseRepository.findAll();
    }
    public ChallengeResponse findByIdResponse(Long id){
        return challengeResponseRepository.findByUserId(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public ChallengeResponse insertResponse(AuxiliaryChallengeResponse auxiliaryChallengeResponse){
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

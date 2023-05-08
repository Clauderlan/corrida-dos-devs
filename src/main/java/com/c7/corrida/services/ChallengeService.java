package com.c7.corrida.services;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.repositories.ChallengeRepository;
import com.c7.corrida.services.exceptions.DatabaseException;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public List<Challenge> findAll(){
        return challengeRepository.findAll();
    }
    public Challenge findById(Long id){ return challengeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));}
    public Challenge insert(Challenge challenge){
        return challengeRepository.save(challenge);
    }
    public Challenge update(Long id,Challenge challenge){
        Challenge challengeCompare = challengeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
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
}

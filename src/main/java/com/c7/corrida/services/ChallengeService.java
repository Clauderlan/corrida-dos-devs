package com.c7.corrida.services;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.repositories.ChallengeRepository;
import com.c7.corrida.services.exceptions.DatabaseException;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

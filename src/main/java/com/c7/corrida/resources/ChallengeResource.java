package com.c7.corrida.resources;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.services.ChallengeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/challenge")
@Resource
public class ChallengeResource {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<List<Challenge>> findAll(){
        List<Challenge> challenges = challengeService.findAll();
        return ResponseEntity.ok().body(challenges);
    }
}

package com.c7.corrida.resources;

import com.c7.corrida.entities.Challenge;
import com.c7.corrida.entities.ChallengeResponse;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.auxiliary.AuxiliaryChallengeResponse;
import com.c7.corrida.entities.contents.ChallengeContent;
import com.c7.corrida.repositories.UserRepository;
import com.c7.corrida.services.ChallengeResponseService;
import com.c7.corrida.services.ChallengeService;
import com.c7.corrida.services.UserService;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/challenge")
@Resource
public class ChallengeResource {

    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private ChallengeResponseService challengeResponseService;
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<Challenge>> findAll(){
        List<Challenge> challenges = challengeService.findAll();
        return ResponseEntity.ok().body(challenges);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Challenge> findById(@PathVariable Long id){
        Challenge challenge = challengeService.findById(id);
        return ResponseEntity.ok().body(challenge);
    }

    @PostMapping
    public ResponseEntity<Challenge> insert(@RequestBody Challenge challenge){
        challenge = challengeService.insert(challenge);
        return ResponseEntity.ok().body(challenge);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Challenge> update(@PathVariable Long id, @RequestBody Challenge challenge){
        challenge = challengeService.update(id,challenge);
        return ResponseEntity.ok().body(challenge);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        challengeService.delete(id);
    }

    // Challenge Response routes

    @GetMapping(value = "/response")
    public ResponseEntity<List<ChallengeResponse>> findAllResponse(){
        List<ChallengeResponse> challengeResponses = challengeResponseService.findAll();
        return ResponseEntity.ok().body(challengeResponses);
    }

    @PostMapping(value = "/response")
    public ResponseEntity<ChallengeResponse> insertResponse(@RequestBody AuxiliaryChallengeResponse auxiliar){
        ChallengeResponse challengeResponse = challengeResponseService.insert(auxiliar);
        return ResponseEntity.ok().body(challengeResponse);
    }


    // Content routes

    @GetMapping(value = "/content/{id}")
    public ResponseEntity<List<ChallengeContent>> findByIdContent(@PathVariable Long id){
        List<ChallengeContent> challengeContents = challengeService.findByIdContent(id);
        return ResponseEntity.ok().body(challengeContents);
    }

}

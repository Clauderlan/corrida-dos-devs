package com.c7.corrida.resources;

import com.c7.corrida.entities.Challenge;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/challenge")
@Resource
public class ChallengeResource {

    @GetMapping
    public ResponseEntity<List<Challenge>> findAll(){
        return null;
    }
}

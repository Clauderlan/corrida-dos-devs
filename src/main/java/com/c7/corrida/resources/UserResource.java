package com.c7.corrida.resources;


import com.c7.corrida.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @GetMapping
    public ResponseEntity<User> findAll(){
        return ResponseEntity.ok().body(new User(1L,"8","292","8@gmail.com",29,"VASCO"));
    }
}

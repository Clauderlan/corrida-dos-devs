package com.c7.corrida.resources;


import com.c7.corrida.entities.User;
import com.c7.corrida.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Resource
public class UserResource {

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user){
        user = userService.insert(user);
        return ResponseEntity.ok().body(user);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}

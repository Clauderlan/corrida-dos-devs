package com.c7.corrida.services;

import com.c7.corrida.entities.User;
import com.c7.corrida.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User insert(User user){
        return userRepository.save(user);
    }
}

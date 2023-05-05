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
    public User findById(Long id){
        return userRepository.findById(id).get();
    }
    public User insert(User user){
        return userRepository.save(user);
    }

    public User update(Long id, User user){
        User userCompare = findById(id);
        updateData(user, userCompare);
        userRepository.save(userCompare);
        return userCompare;
    }

    private void updateData(User user, User userCompare) {
        userCompare.setName(user.getName());
        userCompare.setEmail(user.getEmail());
        userCompare.setBio(user.getBio());
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}

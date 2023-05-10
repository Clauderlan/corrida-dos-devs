package com.c7.corrida.services;

import com.c7.corrida.entities.SocialNetwork;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.auxiliary.AuxiliarySocialNetwork;
import com.c7.corrida.repositories.SocialNetworkRepository;
import com.c7.corrida.repositories.UserRepository;
import com.c7.corrida.services.exceptions.DatabaseException;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public List<User> findTop(){
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "rankPoints"));
        if(users.size() > 10){
            return users.subList(0,10);
        }
        return users;
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
        try {
            userRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    // Social Network

    public List<SocialNetwork> findAllSocial(){
        return socialNetworkRepository.findAll();
    }

    public SocialNetwork insertSocial(AuxiliarySocialNetwork auxiliarySocialNetwork){
        User user = auxiliarySocialNetwork.getUser();
        String socialName = auxiliarySocialNetwork.getSocialName();
        SocialNetwork socialNetwork = new SocialNetwork(null, socialName, user);
        socialNetwork = socialNetworkRepository.save(socialNetwork);
        user.getSocialNetworks().add(socialNetwork);
        userRepository.save(user);
        return socialNetwork;
    }


}

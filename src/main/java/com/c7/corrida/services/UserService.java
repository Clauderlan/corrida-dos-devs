package com.c7.corrida.services;


import com.c7.corrida.entities.SocialNetwork;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.auxiliary.AuxiliarySocialNetwork;
import com.c7.corrida.repositories.CategoryRepository;
import com.c7.corrida.repositories.SocialNetworkRepository;
import com.c7.corrida.repositories.UserRepository;
import com.c7.corrida.security.SecurityConfig;
import com.c7.corrida.services.exceptions.DatabaseException;
import com.c7.corrida.services.exceptions.ResourceExistsException;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserDetailsManager {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    public void alreadyExists(User user){
        if(userExists(user.getUsername())){
            throw new ResourceExistsException(user.getName());
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ResourceExistsException(user.getEmail());
        }
    }

    // GET Methods

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User findByLogin(String login){
        User user = userRepository.findByLogin(login);
        if(user == null){
            throw new ResourceNotFoundException(1L);
        }
        return user;
    }

    public User loadUserByUsername(String username){
        return userRepository.findByName(username);
    }

    public List<User> findTop(){
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "rankPoints"));
        if(users.size() > 10){
            return users.subList(0,10);
        }
        return users;
    }

    // POST, PUT, DELETE Methods

    public User insert(User user){
        alreadyExists(user);
        user = userRepository.save(user);
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        user.getCategory().getUsers().add(user);
        categoryRepository.save(user.getCategory());
        return user;
    }

    public User update(Long id, User user){
        alreadyExists(user);
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
    // GET Methods

    public List<SocialNetwork> findAllSocial(){
        return socialNetworkRepository.findAll();
    }

    public SocialNetwork findByIdSocial(Long id){
        return socialNetworkRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<SocialNetwork> findByUserIdSocial(Long id){
        List<SocialNetwork> socialNetworks = socialNetworkRepository.findByUserId(id);
        if(socialNetworks.size() == 0){
            throw new ResourceNotFoundException(1L);
        }
        return socialNetworks;
    }

    // POST, PUT, DELETE Methods

    public SocialNetwork insertSocial(AuxiliarySocialNetwork auxiliarySocialNetwork){
        User user = auxiliarySocialNetwork.getUser();
        String socialName = auxiliarySocialNetwork.getSocialName();
        SocialNetwork socialNetwork = new SocialNetwork(null, socialName, user);
        socialNetwork = socialNetworkRepository.save(socialNetwork);
        user.getSocialNetworks().add(socialNetwork);
        userRepository.save(user);
        return socialNetwork;
    }

    public SocialNetwork updateSocial(Long id, SocialNetwork socialNetwork){
        SocialNetwork compare = findByIdSocial(id);
        updateDataSocial(socialNetwork, compare);
        socialNetworkRepository.save(compare);
        return compare;
    }

    private void updateDataSocial(SocialNetwork socialNetwork, SocialNetwork compare) {
        compare.setSocialName(socialNetwork.getSocialName());
    }

    public void deleteSocial(Long id){
        try {
            socialNetworkRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    // UserDetails Methods

    @Override
    public void createUser(UserDetails user) {
        insert((User) user);
    }

    @Override
    public void updateUser(UserDetails user) {
        update(loadUserByUsername(user.getUsername()).getId(), (User) user);
    }

    @Override
    public void deleteUser(String username) {
        delete(loadUserByUsername(username).getId());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByName(username);
    }
}

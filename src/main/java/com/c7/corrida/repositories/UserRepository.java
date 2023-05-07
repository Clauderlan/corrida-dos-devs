package com.c7.corrida.repositories;

import com.c7.corrida.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM tb_user where EMAIL = ?", nativeQuery = true)
    public User findByLogin(String login);

}

package com.c7.corrida.repositories;

import com.c7.corrida.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}

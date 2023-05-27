package com.c7.corrida.repositories;

import com.c7.corrida.entities.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long> {

    @Query(value = "SELECT * FROM tb_socialnetwork WHERE user_id = ?", nativeQuery = true)
    public List<SocialNetwork> findByUserId(Long userId);

}

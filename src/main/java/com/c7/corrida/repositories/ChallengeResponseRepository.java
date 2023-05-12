package com.c7.corrida.repositories;

import com.c7.corrida.entities.ChallengeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChallengeResponseRepository extends JpaRepository<ChallengeResponse,Long> {

    @Query(value = "SELECT * FROM tb_challengeresponse WHERE user_id = ?", nativeQuery = true)
    public Optional<ChallengeResponse> findByUserId(Long id);

}

package com.c7.corrida.repositories;

import com.c7.corrida.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    public boolean existsByTitle(String title);
}

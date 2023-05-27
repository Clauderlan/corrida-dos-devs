package com.c7.corrida.repositories.contents;


import com.c7.corrida.entities.contents.ChallengeContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeContentRepository extends JpaRepository<ChallengeContent, Long> {
    @Query(value = "SELECT * FROM tb_challengecontent WHERE id_challenge = ?", nativeQuery = true)
    public List<ChallengeContent> findByChallenge(Long id);

}

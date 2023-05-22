package com.c7.corrida.repositories;

import com.c7.corrida.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    public boolean existsByTitle(String title);

}

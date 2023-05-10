package com.c7.corrida.repositories.contents;

import com.c7.corrida.entities.contents.MaterialContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialContentRepository extends JpaRepository<com.c7.corrida.entities.contents.MaterialContent, Long> {

    @Query(value = "SELECT * FROM tb_materialcontent WHERE id_material = ?", nativeQuery = true)
    public List<MaterialContent> findByMaterial(Long id);
}

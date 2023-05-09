package com.c7.corrida.services;

import com.c7.corrida.entities.Material;
import com.c7.corrida.repositories.MaterialRepository;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> findAll(){
        return materialRepository.findAll();
    }

    public Material findById(Long id){
        return materialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}

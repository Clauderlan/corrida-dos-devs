package com.c7.corrida.services;

import com.c7.corrida.entities.Material;
import com.c7.corrida.repositories.MaterialRepository;
import com.c7.corrida.services.exceptions.DatabaseException;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Material insert(Material material){
        return materialRepository.save(material);
    }

    public void delete(Long id){
        try {
            materialRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}

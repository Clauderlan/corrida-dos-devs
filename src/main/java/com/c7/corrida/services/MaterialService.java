package com.c7.corrida.services;

import com.c7.corrida.entities.Material;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.contents.ChallengeContent;
import com.c7.corrida.entities.contents.MaterialContent;
import com.c7.corrida.repositories.MaterialRepository;
import com.c7.corrida.repositories.contents.MaterialContentRepository;
import com.c7.corrida.services.exceptions.DatabaseException;
import com.c7.corrida.services.exceptions.ResourceExistsException;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialContentRepository materialContentRepository;

    public void alreadyExists(Material material){
        if(materialRepository.existsByTitle(material.getTitle())){
            throw new ResourceExistsException(material.getTitle());
        }
    }

    // GET Methods

    public List<Material> findAll(){
        return materialRepository.findAll();
    }

    public Material findById(Long id){
        return materialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<MaterialContent> findByIdContent(Long id){
        return materialContentRepository.findByMaterial(id);
    }

    // POST, PUT, DELETE Methods

    public Material insert(Material material){
        alreadyExists(material);
        if(material.getMaterialContent().size() > 0){
            for(MaterialContent x : material.getMaterialContent()){
                materialContentRepository.save(x);
            }
        }
        return materialRepository.save(material);
    }

    public Material update(Long id, Material material){
        alreadyExists(material);
        Material compare = findById(id);
        for(MaterialContent x : material.getMaterialContent()){
            materialContentRepository.save(x);
        }
        updateData(compare, material);
        materialRepository.save(compare);
        return compare;
    }

    private void updateData(Material compare, Material material) {
        compare.setTitle(material.getTitle());
        compare.setIdealFor(material.getIdealFor());
        compare.setDetailedInformation(material.getDetailedInformation());
        compare.setUrlImage(material.getUrlImage());
        compare.setShortInformation(material.getShortInformation());
        compare.getMaterialContent().addAll(material.getMaterialContent());
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

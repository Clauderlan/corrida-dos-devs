package com.c7.corrida.resources;

import com.c7.corrida.entities.Material;
import com.c7.corrida.services.MaterialService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/material")
@Resource
public class MaterialResource {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<Material>> findAll(){
        List<Material> materials = materialService.findAll();
        return ResponseEntity.ok().body(materials);

    }
}

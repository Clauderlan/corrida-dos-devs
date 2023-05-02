package com.c7.corrida.resources;

import com.c7.corrida.entities.Material;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/material")
@Resource
public class MaterialResource {

    @GetMapping
    public List<Material> findAll(){
        
    }
}

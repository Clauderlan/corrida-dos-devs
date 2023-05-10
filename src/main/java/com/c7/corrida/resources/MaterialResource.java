package com.c7.corrida.resources;

import com.c7.corrida.entities.Material;
import com.c7.corrida.entities.contents.MaterialContent;
import com.c7.corrida.services.MaterialService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Material> findyById(@PathVariable Long id){
        Material material = materialService.findById(id);
        return ResponseEntity.ok().body(material);
    }

    @GetMapping(value = "/content/{id}")
    public ResponseEntity<List<MaterialContent>> findById(@PathVariable Long id){
        List<MaterialContent> materialContent = materialService.findByIdContent(id);
        return ResponseEntity.ok().body(materialContent);
    }
    @PostMapping
    public ResponseEntity<Material> insert(@RequestBody Material material){
        material = materialService.insert(material);
        return ResponseEntity.ok().body(material);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Material> update(@PathVariable Long id, @RequestBody Material material){
        material = materialService.update(id, material);
        return ResponseEntity.ok().body(material);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        materialService.delete(id);
    }
}

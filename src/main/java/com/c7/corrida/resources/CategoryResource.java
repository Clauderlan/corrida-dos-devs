package com.c7.corrida.resources;

import com.c7.corrida.entities.Category;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@Resource
public class CategoryResource {

    public ResponseEntity<List<Category>> findAll(){
        return null;
    }
}

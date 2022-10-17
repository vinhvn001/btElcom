package com.elcom.backend.controller;

import com.elcom.data.model.log.SchoolClass;
import com.elcom.data.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @GetMapping("/{id}")
    public SchoolClass findById(@PathVariable Long id){
        return schoolClassService.findById(id);
    }


    @GetMapping
    public List<SchoolClass> findAll(){
        return schoolClassService.findAll();
    }

    @PostMapping
    public SchoolClass create(@RequestBody SchoolClass schoolClass){
        return schoolClassService.create(schoolClass);
    }

    @PutMapping
    public SchoolClass update(@RequestBody SchoolClass schoolClass){
        return schoolClassService.update(schoolClass);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        schoolClassService.delete(id);
    }
}

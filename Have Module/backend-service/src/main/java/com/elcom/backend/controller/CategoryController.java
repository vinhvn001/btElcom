package com.elcom.backend.controller;


import com.elcom.data.model.library.Category;
import com.elcom.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){

        if(categoryService.findById(id) == null)
            throw new ValidationException("Id không hợp lệ");
        return categoryService.findById(id);
    }

    @PostMapping
    public Category create(@RequestBody String categoryName){

        if(categoryName == null)
            throw new ValidationException("Field không được để trống");
        return categoryService.create(categoryName);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody String categoryName){
        if(categoryService.findById(id) == null)
            throw new ValidationException("Id không tồn tại");
        return categoryService.update(id, categoryName);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        if(categoryService.findById(id) == null)
            throw new ValidationException("Id không tồn tại");
        categoryService.delete(id);
    }


    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }
}

package com.elcom.data.service;


import com.elcom.data.model.library.Category;

public interface CategoryService {


     Category findById(Long id);

    Category create(String categoryName );

    Category update(Long id, String categoryName);

    void delete(Long id);

    Iterable<Category> findAll();
}

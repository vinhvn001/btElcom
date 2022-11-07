package com.elcom.library.service;


import com.elcom.library.model.Category;

import java.util.List;


public interface CategoryService {


     Category findById(Long id);

    Category create(String categoryName );

    Category update(Long id, String categoryName);

    void delete(Long id);

    List<Category> findAll();

    Category findByCategoryName(String categoryName);
}

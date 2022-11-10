package com.elcom.elastic.service;


import com.elcom.elastic.model.CategoryEs;

import java.util.List;

public interface CategoryEsService {
    CategoryEs save(CategoryEs CategoryEs);

    CategoryEs update(Long id, String name);

    void delete(Long id);

    CategoryEs findById(Long id);

    Iterable<CategoryEs> findAll();

    CategoryEs findByName(String name);
}

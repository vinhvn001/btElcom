package com.elcom.elastic.service.impl;

import com.elcom.elastic.model.CategoryEs;
import com.elcom.elastic.repository.CategoryEsRepository;
import com.elcom.elastic.service.CategoryEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryEsServiceImpl implements CategoryEsService {
    @Autowired
    private CategoryEsRepository categoryEsRepository;

    @Override
    public CategoryEs save(CategoryEs categoryEs) {
        return categoryEsRepository.save(categoryEs);
    }

    @Override
    public CategoryEs update(Long id, String name) {
        CategoryEs categoryEs = categoryEsRepository.findById(id).orElse(null);
        categoryEs.setName(name);
        return categoryEsRepository.save(categoryEs);
    }

    @Override
    public void delete(Long id) {
        categoryEsRepository.deleteById(id);
    }

    @Override
    public CategoryEs findById(Long id) {
        return categoryEsRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<CategoryEs> findAll() {
        return categoryEsRepository.findAll();
    }

    @Override
    public CategoryEs findByName(String name) {
        return categoryEsRepository.findByname(name);
    }
}

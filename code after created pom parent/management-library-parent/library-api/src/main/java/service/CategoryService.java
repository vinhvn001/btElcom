package service;


import entity.Category;



public interface CategoryService {


     Category findById(Long id);

    Category create(Category category );

    Category update(Long id, Category category);

    void delete(Long id);

    Iterable<Category> findAll();
}

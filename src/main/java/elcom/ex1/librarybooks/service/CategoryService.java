package elcom.ex1.librarybooks.service;


import elcom.ex1.librarybooks.entity.library.Category;



public interface CategoryService {


     Category findById(Long id);

    Category create(Category category );

    Category update(Long id, Category category);

    void delete(Long id);

    Iterable<Category> findAll();
}

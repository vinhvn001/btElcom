package elcom.ex1.librarybooks.service;


import elcom.ex1.librarybooks.entity.library.Category;



public interface CategoryService {


     Category findById(Long id);

    Category create(String categoryName );

    Category update(Long id, String categoryName);

    void delete(Long id);

    Iterable<Category> findAll();
}

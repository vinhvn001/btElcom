package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.Category;



public interface CategoryService {


     Category findById(Long ID);

    Category create(Category category );

    Category update(Long ID, Category category);

    void delete(Long ID);

    Iterable<Category> findAll();
}

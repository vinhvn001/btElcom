package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.CATEGORY;

public interface CategoryService {

    CATEGORY findById(Long ID);

    CATEGORY create( CATEGORY category );

    CATEGORY update(Long ID, CATEGORY category);

    void delete(Long ID);

    Iterable<CATEGORY> findAll();
}

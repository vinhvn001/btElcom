package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.AUTHOR;


import java.util.List;

public interface AuthorService {
    AUTHOR findById(Long ID);

    AUTHOR create( AUTHOR book );

    AUTHOR update(Long ID, AUTHOR book);

    void delete(Long ID);

    Iterable<AUTHOR> findAll();

}
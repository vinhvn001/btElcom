package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.BOOKS;

import java.util.List;

public interface BookService {

    BOOKS findById(Long ID);

    BOOKS create( BOOKS book );

    BOOKS update(Long ID, BOOKS book);

    void delete(Long ID);

    Iterable<BOOKS> findAll();

}

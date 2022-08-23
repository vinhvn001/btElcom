package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.Books;

public interface BookService {

    Books findById(Long ID);

    Books create(Books book );

    Books update(Long ID, Books book);

    void delete(Long ID);

    Iterable<Books> findAll();

}

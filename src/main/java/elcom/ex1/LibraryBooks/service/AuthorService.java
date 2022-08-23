package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.Author;

public interface AuthorService {
    Author findById(Long ID);

    Author create(Author author );

    Author update(Long ID, Author author);

    void delete(Long ID);

    Iterable<Author> findAll();

}
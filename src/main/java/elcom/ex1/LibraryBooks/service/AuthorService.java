package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.Author;

public interface AuthorService {
    Author findById(Long id);

    Author create(Author author );

    Author update(Long id, Author author);

    void delete(Long id);

    Iterable<Author> findAll();

}
package elcom.ex1.librarybooks.service;


import elcom.ex1.librarybooks.entity.Author;

public interface AuthorService {
    Author findById(Long id);

    Author create(Author author );

    Author update(Long id, Author author);

    void delete(Long id);

    Iterable<Author> findAll();

}
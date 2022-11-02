package elcom.ex1.librarybooks.service;


import elcom.ex1.librarybooks.entity.library.Author;

public interface AuthorService {
    Author findById(Long id);

    Author create(String authorName );

    Author update(Long id, String authorName);

    void delete(Long id);

    Iterable<Author> findAll();

}
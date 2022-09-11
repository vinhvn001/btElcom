package service;


import entity.Author;
import entity.Book;
import entity.Category;


public interface BookService {

    Book findById(Long id);

    Book create(Book book );

    Book update(Long id, Book book);

    void delete(Long id);

    Iterable<Book> findAll();


    Integer findBookAmountByAuthorId(Author id);

    Integer findBookAmountByFirstLetter(String firstLetter);

    Integer findBookAmountByCategoryId(Category id);
}

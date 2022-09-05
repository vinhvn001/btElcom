package elcom.ex1.librarybooks.service;


import elcom.ex1.librarybooks.entity.Author;
import elcom.ex1.librarybooks.entity.Book;
import elcom.ex1.librarybooks.entity.Category;


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
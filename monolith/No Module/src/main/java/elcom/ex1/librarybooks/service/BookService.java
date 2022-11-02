package elcom.ex1.librarybooks.service;


import elcom.ex1.librarybooks.entity.library.Author;
import elcom.ex1.librarybooks.entity.library.Book;
import elcom.ex1.librarybooks.entity.library.Category;

import java.util.List;


public interface BookService {

    Book findById(Long id);

    Book create(Book book);

    Book update( Book book);

    void delete(Long id);

    Iterable<Book> findAll();


    List<Object[]> findBookAmountByAuthorId(Author id);

    List<Object[]> findBookAmountByFirstLetter(String firstLetter);

    Integer findBookAmountByCategoryId(Category id);

    List<Object[]> findBookList();
}

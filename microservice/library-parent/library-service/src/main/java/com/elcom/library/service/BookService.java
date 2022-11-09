package com.elcom.library.service;


import com.elcom.library.model.Author;
import com.elcom.library.model.Book;
import com.elcom.library.model.Category;


import java.util.List;


public interface BookService {

    Book findById(Long id);

    Book create(Book book);

    Book update( Book book);

    void delete(Long id);

    List<Book> findAll();


    List<Object[]> findBookAmountByAuthorId(Author id);

    List<Object[]> findBookAmountByFirstLetter(String firstLetter);

    Integer findBookAmountByCategoryId(Category id);

    List<Object[]> findBookList();

    Book findByBookName(String bookName);

    void borrowOne(String bookName);

    void returnOne(String bookName);
}

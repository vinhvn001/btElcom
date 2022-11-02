package com.elcom.data.service;



import com.elcom.data.model.library.Author;
import com.elcom.data.model.library.Book;
import com.elcom.data.model.library.Category;

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

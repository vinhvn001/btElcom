package com.elcom.data.service.impl;


import com.elcom.data.model.library.Author;
import com.elcom.data.model.library.Book;
import com.elcom.data.model.library.Category;
import com.elcom.data.repository.library.BookRepository;
import com.elcom.data.service.BookService;
import com.elcom.elastic.model.BookEs;
import com.elcom.elastic.service.BookEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private BookEsService bookEsService;

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book create(Book book) {
        bookRepository.save(book);
        BookEs bookEs = new BookEs(book.getId(), book.getBookName());
        bookEsService.save(bookEs);
        return book;
    }

    @Override
    public Book update( Book book) {
        Book fromDB = bookRepository.findById(book.getId()).orElse(null);
        if(fromDB == null)
            return null;
        else{
            fromDB.setCategoryId(book.getCategoryId());
            fromDB.setBookAmount(book.getBookAmount());
            fromDB.setFirstLetter(book.getFirstLetter());
            fromDB.setBookName(book.getBookName());
            fromDB.setAuthorId(book.getAuthorId());

            bookEsService.update( book.getId(),book.getBookName());
            return bookRepository.save(fromDB);
        }
    }

    @Override
    public void delete(Long id) {

        bookEsService.delete(id);
        bookRepository.deleteById(id);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Object[]> findBookAmountByAuthorId(Author id) {

        return bookRepository.findBookAmountByAuthorId(id);
    }

    @Override
    public List<Object[]> findBookAmountByFirstLetter(String firstLetter) {
        return bookRepository.findBookAmountByFirstLetter(firstLetter);
    }

    @Override
    public Integer findBookAmountByCategoryId(Category id) {
        return bookRepository.findBookAmountByCategoryId(id);
    }

    @Override
    public List<Object[]> findBookList() {
        return bookRepository.findBookList();
    }




}

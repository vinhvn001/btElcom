package com.elcom.elastic.service.impl;

import com.elcom.elastic.model.BookEs;
import com.elcom.elastic.repository.BookEsRepository;
import com.elcom.elastic.service.BookEsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookEsServiceImpl implements BookEsService {
    @Autowired
    private BookEsRepository bookEsRepository;


    @Override
    public BookEs save(BookEs bookEs) {
        return bookEsRepository.save(bookEs);
    }

    @Override
    public BookEs update( Long id, String name) {
        BookEs bookEs = bookEsRepository.findById(id).orElse(null);
        bookEs.setName(name);
        return bookEsRepository.save(bookEs);
    }

    @Override
    public void delete(Long id) {
        bookEsRepository.deleteById(id);
    }

    @Override
    public BookEs findById(Long id) {

        return bookEsRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<BookEs> findAll() {
        return bookEsRepository.findAll();
    }

    @Override
    public List<BookEs> findByName(String name) {

        return bookEsRepository.findByName(name);
    }
}

package com.elcom.elastic.service;

import com.elcom.elastic.model.BookEs;


import java.util.List;

public interface BookEsService {

    BookEs save(BookEs bookEs);

    BookEs update(Long id, String name);

    void delete(Long id);

    BookEs findById(Long id);

    Iterable<BookEs> findAll();

    List<BookEs> findByName(String name);
}

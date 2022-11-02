package com.elcom.data.service;


import com.elcom.data.model.library.Author;

public interface AuthorService {
    Author findById(Long id);

    Author create(String authorName );

    Author update(Long id, String authorName);

    void delete(Long id);

    Iterable<Author> findAll();

}
package com.elcom.library.service;


import com.elcom.library.model.Author;

import java.util.List;


public interface AuthorService {
    Author findById(Long id);

    Author create(String authorName );

    Author update(Long id, String authorName);

    void delete(Long id);

    Author findByAuthorName(String authorName);

    List<Author> findAll();

}
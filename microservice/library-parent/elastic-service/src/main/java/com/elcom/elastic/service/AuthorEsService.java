package com.elcom.elastic.service;

import com.elcom.elastic.model.AuthorEs;

import java.util.List;

public interface AuthorEsService {
    AuthorEs save(AuthorEs authorEs);

    AuthorEs update(Long id, String name);

    void delete(Long id);

    AuthorEs findById(Long id);

    Iterable<AuthorEs> findAll();

    AuthorEs findByName(String name);
}

package com.elcom.elastic.service.impl;

import com.elcom.elastic.model.AuthorEs;
import com.elcom.elastic.repository.AuthorEsRepository;
import com.elcom.elastic.service.AuthorEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorEsServiceImpl implements AuthorEsService {

    @Autowired
    private AuthorEsRepository authorEsRepository;

    public AuthorEs save(AuthorEs authorEs){
        return authorEsRepository.save(authorEs);
    }

    @Override
    public AuthorEs update(Long id, String name) {
        AuthorEs authorEs = authorEsRepository.findById(id).orElse(null);
        authorEs.setName(name);
        return authorEsRepository.save(authorEs);
    }

    @Override
    public void delete(Long id) {
         authorEsRepository.deleteById(id);
    }

    public AuthorEs findById(Long id){
        return authorEsRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<AuthorEs> findAll() {
        return authorEsRepository.findAll();
    }

    @Override
    public AuthorEs findByName(String name) {
        return authorEsRepository.findByName(name);
    }


}

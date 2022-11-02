package com.elcom.data.service.impl;


import com.elcom.data.model.library.Author;
import com.elcom.data.repository.library.AuthorRepository;
import com.elcom.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;



    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;

    }

    @Override
    @Cacheable(key = "#p0", value ="author")
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author create(String authorName) {
        Author author = new Author();
        author.setAuthorName(authorName);
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long ID, String authorName) {
        Author fromDB = authorRepository.findById(ID).orElse(null);
        if(fromDB == null){
            return null;
        }
        else{
            fromDB.setAuthorName(authorName);
            return authorRepository.save(fromDB);
        }
    }


    @Override
    @CacheEvict(key = "#p0", value = "author")
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Iterable<Author> findAll() {

        return authorRepository.findAll();
    }
}

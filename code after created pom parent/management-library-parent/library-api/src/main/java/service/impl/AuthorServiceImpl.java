package service.impl;

import entity.Author;
import repository.AuthorRepository;
import service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author create(Author author) {
        if(author.getId() == null){
            return null;
        }
        if(author.getAuthorName() == null){
            return null;
        }
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long ID, Author author) {
        Author fromDB = authorRepository.findById(ID).orElse(null);
        if(fromDB == null){
            return null;
        }
        else{
            fromDB.setId(author.getId());
            fromDB.setAuthorName(author.getAuthorName());
            return authorRepository.save(fromDB);
        }
    }


    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Iterable<Author> findAll() {

        return authorRepository.findAll();
    }
}

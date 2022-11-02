package elcom.ex1.librarybooks.service.impl;

import elcom.ex1.librarybooks.entity.library.Author;
import elcom.ex1.librarybooks.repository.library.AuthorRepository;
import elcom.ex1.librarybooks.service.AuthorService;
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

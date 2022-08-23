package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.entity.Author;
import elcom.ex1.LibraryBooks.repository.AuthorRepository;
import elcom.ex1.LibraryBooks.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author findById(Long ID) {
        return authorRepository.findById(ID).orElse(null);
    }

    @Override
    public Author create(Author author) {
        if(author.getID() == null){
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
            fromDB.setID(author.getID());
            fromDB.setAuthorName(author.getAuthorName());
            return authorRepository.save(fromDB);
        }
    }


    @Override
    public void delete(Long ID) {
        authorRepository.deleteById(ID);
    }

    @Override
    public Iterable<Author> findAll() {

        return authorRepository.findAll();
    }
}

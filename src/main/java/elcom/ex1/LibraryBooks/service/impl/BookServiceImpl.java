package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.entity.Books;
import elcom.ex1.LibraryBooks.repository.BooksRepository;
import elcom.ex1.LibraryBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BooksRepository booksRepository;

    @Override
    public Books findById(Long ID) {
        return booksRepository.findById(ID).orElse(null);
    }

    @Override
    public Books create(Books book) {
        if(book.getID() == null || book.getBookName() == null || book.getBookAmount() == null )
            return null;
        return booksRepository.save(book);
    }

    @Override
    public Books update(Long ID, Books book) {
        Books fromDB = booksRepository.findById(ID).orElse(null);
        if(fromDB == null)
            return null;
        else{
            fromDB.setID(book.getID());
            fromDB.setCategory(book.getCategory());
            fromDB.setBookAmount(book.getBookAmount());
            fromDB.setBookName(book.getBookName());
            fromDB.setAuthor(book.getAuthor());
            return booksRepository.save(fromDB);
        }
    }

    @Override
    public void delete(Long ID) {
         booksRepository.deleteById(ID);
    }

    @Override
    public Iterable<Books> findAll() {
        return booksRepository.findAll();
    }
}

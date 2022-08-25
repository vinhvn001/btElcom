package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.entity.Book;
import elcom.ex1.LibraryBooks.repository.BookRepository;
import elcom.ex1.LibraryBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findById(Long ID) {
        return bookRepository.findById(ID).orElse(null);
    }

    @Override
    public Book create(Book book) {
        if(book.getID() == null || book.getBookName() == null || book.getBookAmount() == null )
            return null;
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long ID, Book book) {
        Book fromDB = bookRepository.findById(ID).orElse(null);
        if(fromDB == null)
            return null;
        else{
            fromDB.setID(book.getID());
            fromDB.setCategoryId(book.getCategoryId());
            fromDB.setBookAmount(book.getBookAmount());
            fromDB.setBookName(book.getBookName());
            fromDB.setAuthorId(book.getAuthorId());
            return bookRepository.save(fromDB);
        }
    }

    @Override
    public void delete(Long ID) {
         bookRepository.deleteById(ID);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookAmountByAuthorId(Long ID) {
        return bookRepository.findBookAmountByAuthorId(ID);
    }

    @Override
    public Book findBookAmountByFirstLetter(String firstLetter) {
        return bookRepository.findBookAmountByFirstLetter(firstLetter);
    }

    @Override
    public Book findBookAmountByCategoryId(Long ID) {
        return bookRepository.findBookAmountByCategoryId(ID);
    }


}

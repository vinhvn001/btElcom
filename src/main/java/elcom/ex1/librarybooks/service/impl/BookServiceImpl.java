package elcom.ex1.librarybooks.service.impl;

import elcom.ex1.librarybooks.entity.Author;
import elcom.ex1.librarybooks.entity.Book;
import elcom.ex1.librarybooks.entity.Category;
import elcom.ex1.librarybooks.repository.BookRepository;
import elcom.ex1.librarybooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book create(Book book) {
        if(book.getId() == null || book.getBookName() == null || book.getBookAmount() == null )
            return null;
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        Book fromDB = bookRepository.findById(id).orElse(null);
        if(fromDB == null)
            return null;
        else{
            fromDB.setId(book.getId());
            fromDB.setCategoryId(book.getCategoryId());
            fromDB.setBookAmount(book.getBookAmount());
            fromDB.setBookName(book.getBookName());
            fromDB.setAuthorId(book.getAuthorId());
            return bookRepository.save(fromDB);
        }
    }

    @Override
    public void delete(Long id) {
         bookRepository.deleteById(id);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Integer findBookAmountByAuthorId(Author id) {

        return bookRepository.findBookAmountByAuthorId(id);
    }

    @Override
    public Integer findBookAmountByFirstLetter(String firstLetter) {
        return bookRepository.findBookAmountByFirstLetter(firstLetter);
    }

    @Override
    public Integer findBookAmountByCategoryId(Category id) {
        return bookRepository.findBookAmountByCategoryId(id);
    }


}

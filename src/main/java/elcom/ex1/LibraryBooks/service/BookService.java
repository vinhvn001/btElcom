package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.Book;
import org.springframework.data.jpa.repository.Query;


public interface BookService {

    Book findById(Long id);

    Book create(Book book );

    Book update(Long id, Book book);

    void delete(Long id);

    Iterable<Book> findAll();


    Book findBookAmountByAuthorId(Long id);

    Book findBookAmountByFirstLetter(String firstLetter);

    Book findBookAmountByCategoryId(Long id);
}

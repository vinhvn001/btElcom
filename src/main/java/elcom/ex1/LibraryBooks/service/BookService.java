package elcom.ex1.LibraryBooks.service;


import elcom.ex1.LibraryBooks.entity.Book;
import org.springframework.data.jpa.repository.Query;


public interface BookService {

    Book findById(Long ID);

    Book create(Book book );

    Book update(Long ID, Book book);

    void delete(Long ID);

    Iterable<Book> findAll();


    Book findBookAmountByAuthorId(Long ID);

    Book findBookAmountByFirstLetter(String firstLetter);

    Book findBookAmountByCategoryId(Long ID);
}

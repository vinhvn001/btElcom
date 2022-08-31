package elcom.ex1.librarybooks.service;


import elcom.ex1.librarybooks.entity.Book;


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

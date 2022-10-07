package elcom.ex1.librarybooks.service;

import elcom.ex1.librarybooks.entity.elastic.BookEs;

import java.util.List;

public interface BookEsService {

    BookEs save(BookEs bookEs);

    BookEs update(Long id, String name);

    void delete(Long id);

    BookEs findById(Long id);

    Iterable<BookEs> findAll();

    List<BookEs> findByName(String name);
}
